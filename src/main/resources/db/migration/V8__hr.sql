-- V8__hr.sql
-- Create HR tables

DROP TABLE IF EXISTS shift_schedules;
DROP TABLE IF EXISTS payrolls;
DROP TABLE IF EXISTS employee_roles;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    id CHAR(36) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    description VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_roles_name UNIQUE (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE employees (
    id CHAR(36) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    is_deleted BIT NOT NULL,
    deleted_at DATETIME(6),
    credentials_hash VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    hire_date DATE NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    status VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_employees_email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE employee_roles (
    id CHAR(36) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    assigned_date DATE NOT NULL,
    end_date DATE,
    employee_id CHAR(36) NOT NULL,
    role_id CHAR(36) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_employee_roles_employee FOREIGN KEY (employee_id) REFERENCES employees (id),
    CONSTRAINT fk_employee_roles_role FOREIGN KEY (role_id) REFERENCES roles (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE payrolls (
    id CHAR(36) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    is_deleted BIT NOT NULL,
    deleted_at DATETIME(6),
    deductions DECIMAL(10,2) NOT NULL,
    gross_pay DECIMAL(10,2) NOT NULL,
    net_pay DECIMAL(10,2) NOT NULL,
    paid_at DATETIME(6),
    period_end DATE NOT NULL,
    period_start DATE NOT NULL,
    employee_id CHAR(36) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_payrolls_employee FOREIGN KEY (employee_id) REFERENCES employees (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE shift_schedules (
    id CHAR(36) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    end_time DATETIME(6) NOT NULL,
    location VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    start_time DATETIME(6) NOT NULL,
    employee_id CHAR(36) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_shift_schedules_employee FOREIGN KEY (employee_id) REFERENCES employees (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
