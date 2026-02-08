-- V8__hr.sql
-- Create HR tables

CREATE TABLE roles (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    description VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_roles_name UNIQUE (name)
) ENGINE=InnoDB;

CREATE TABLE employees (
    id BINARY(16) NOT NULL,
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
) ENGINE=InnoDB;

CREATE TABLE employee_roles (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    assigned_date DATE NOT NULL,
    end_date DATE,
    employee_id BINARY(16) NOT NULL,
    role_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_employee_roles_employee FOREIGN KEY (employee_id) REFERENCES employees (id),
    CONSTRAINT fk_employee_roles_role FOREIGN KEY (role_id) REFERENCES roles (id)
) ENGINE=InnoDB;

CREATE TABLE payrolls (
    id BINARY(16) NOT NULL,
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
    employee_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_payrolls_employee FOREIGN KEY (employee_id) REFERENCES employees (id)
) ENGINE=InnoDB;

CREATE TABLE shift_schedules (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(100),
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(100),
    end_time DATETIME(6) NOT NULL,
    location VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    start_time DATETIME(6) NOT NULL,
    employee_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_shift_schedules_employee FOREIGN KEY (employee_id) REFERENCES employees (id)
) ENGINE=InnoDB;
