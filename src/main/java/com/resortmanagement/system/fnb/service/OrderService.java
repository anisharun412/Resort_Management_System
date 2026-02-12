package com.resortmanagement.system.fnb.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.booking.repository.BookingGuestRepository;
import com.resortmanagement.system.booking.repository.ReservationRepository;
import com.resortmanagement.system.fnb.dto.request.OrderRequest;
import com.resortmanagement.system.fnb.dto.response.OrderResponse;
import com.resortmanagement.system.fnb.entity.Order;
import com.resortmanagement.system.fnb.entity.OrderItem;
import com.resortmanagement.system.fnb.mapper.OrderMapper;
import com.resortmanagement.system.fnb.repository.MenuItemRepository;
import com.resortmanagement.system.fnb.repository.OrderRepository;
import com.resortmanagement.system.inventory.service.InventoryTransactionService;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderMapper orderMapper;
    private final InventoryTransactionService inventoryTransactionService;
    private final BookingGuestRepository bookingGuestRepository;
    private final ReservationRepository reservationRepository;

    public OrderService(
            OrderRepository orderRepository,
            MenuItemRepository menuItemRepository,
            OrderMapper orderMapper,
            InventoryTransactionService inventoryTransactionService,
            BookingGuestRepository bookingGuestRepository,
            ReservationRepository reservationRepository
        ) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderMapper = orderMapper;
        this.inventoryTransactionService = inventoryTransactionService;
        this.bookingGuestRepository = bookingGuestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAllActive().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<OrderResponse> findById(UUID id) {
        return orderRepository.findByIdAndDeletedFalse(id).map(orderMapper::toResponse);
    }

    @Transactional
    public OrderResponse create(OrderRequest request) {
        Order order = orderMapper.toEntity(request);
        order.setGuestId(
            request.getGuestId() != null ? 
            bookingGuestRepository.findByIdAndDeletedFalse(request.getGuestId()
        ).orElse(null) : null);
        order.setReservationId(
            request.getReservationId() != null ? 
            reservationRepository.findByIdAndDeletedFalse(request.getReservationId()
        ).orElse(null) : null);
        order.setPlacedAt(Instant.now());
        // order.setStatus(OrderStatus.PENDING); // Assuming status exists or default
        
        // Calculate total and validate items
        BigDecimal totalAmount = BigDecimal.ZERO;
        
        // We need to save order to get ID for items? Or Cascade?
        // Assuming we set up items first
        // Need to iterate items to calculate total and verify menu items
        
        // Note: OrderMapper.toEntity set basic fields, but items logic is here
        
        // To properly implement items, I need OrderItemRepository or Cascade in Order.
        // I will assume CascadeType.ALL on Order.items based on entity check (I didn't check Order entity fields fully, only OrderItem fields).
        // Let's assume we need to construct OrderItems and add to Order.
        
        // Map to hold ingredients to consume: InventoryItemId -> Qty
        java.util.Map<UUID, BigDecimal> ingredientsToConsume = new java.util.HashMap<>();

        if (request.getItems() != null) {
            for (com.resortmanagement.system.fnb.dto.request.OrderItemRequest itemReq : request.getItems()) {
                com.resortmanagement.system.fnb.entity.MenuItem menuItem = menuItemRepository.findById(itemReq.getMenuItemId())
                        .orElseThrow(() -> new RuntimeException("Menu item not found: " + itemReq.getMenuItemId()));
                
                if (!menuItem.isAvailable()) {
                     throw new RuntimeException("Menu item is not available: " + menuItem.getName());
                }

                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setMenuItem(menuItem);
                orderItem.setQuantity(itemReq.getQty());
                orderItem.setUnitPrice(menuItem.getPrice());
                orderItem.setTotalPrice(menuItem.getPrice().multiply(BigDecimal.valueOf(itemReq.getQty())));
                
                // Add to order
                order.getOrderItems().add(orderItem);
                
                // Calculating ingredients
                menuItem.getIngredients().forEach(ing -> {
                    BigDecimal totalRequired = ing.getQuantityRequired().multiply(BigDecimal.valueOf(itemReq.getQty()));
                    ingredientsToConsume.merge(ing.getInventoryItem().getId(), totalRequired, BigDecimal::add);
                });
                
                totalAmount = totalAmount.add(orderItem.getTotalPrice());
            }
        }
        
        order.setTotalAmount(totalAmount);
        
        // Save Order (cascades items) - IF fields exist.
        // I suspect Order entity is incomplete. I need to fix it.
        // For now, I'll write the service and then fix the entity.
        
        Order savedOrder = orderRepository.save(order);
        
        // Consume inventory
        if (!ingredientsToConsume.isEmpty()) {
            inventoryTransactionService.consumeIngredients(
                    ingredientsToConsume, 
                    com.resortmanagement.system.inventory.entity.InventorySourceType.ORDER, 
                    savedOrder.getId());
        }

        return orderMapper.toResponse(savedOrder);
    }
}
