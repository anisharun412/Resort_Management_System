package com.resortmanagement.system.hr.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resortmanagement.system.hr.entity.Payroll;
import com.resortmanagement.system.hr.repository.PayrollRepository;

@Service
public class PayrollService {

    private final PayrollRepository repository;

    public PayrollService(PayrollRepository repository) {
        this.repository = repository;
    }

    public Page<Payroll> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Payroll> findById(UUID id) {
        return repository.findById(id);
    }

    public Payroll update(UUID id, Payroll entity) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setEmployee(entity.getEmployee());
                    existing.setPeriodStart(entity.getPeriodStart());
                    existing.setPeriodEnd(entity.getPeriodEnd());
                    existing.setGrossPay(entity.getGrossPay());
                    existing.setDeductions(entity.getDeductions());
                    existing.setNetPay(entity.getNetPay()); // or recalculate
                    existing.setPaidAt(entity.getPaidAt());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Payroll not found with id " + id));
    }

    public Payroll save(Payroll entity) {
        if (entity.getEmployee() == null) {
            throw new IllegalArgumentException("Employee is required for payroll");
        }
        if (entity.getPeriodStart().isAfter(entity.getPeriodEnd())) {
            throw new IllegalArgumentException("Period start cannot be after period end");
        }
        // Auto-calculate net pay if not set or verify it
        if (entity.getNetPay() == null) {
            entity.setNetPay(entity.getGrossPay().subtract(entity.getDeductions()));
        }
        return repository.save(entity);
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Payroll record not found with id " + id);
        }
        repository.deleteById(id);
    }

    public List<Payroll> findByEmployee(UUID employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
}
