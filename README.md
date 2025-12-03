# Documentación del Sistema de Gestión de Empleados

## 📋 Descripción del Proyecto

Este sistema es una aplicación Spring Boot para la gestión integral de empleados, salarios y roles. Implementa una arquitectura limpia con separación clara de responsabilidades y múltiples patrones de diseño para manejar diferentes aspectos del dominio de negocio.

### 🎯 Funcionalidades Principales

1. **Gestión de Empleados**
    - Crear, leer, actualizar empleados
    - Clasificación por tipos: FIJO, POR_HORAS, COMISION
    - Asignación de roles y salarios

2. **Cálculo de Salarios**
    - Estrategias de cálculo según tipo de empleado
    - Cálculo con bonificaciones (antigüedad, desempeño)
    - Registro de horas extras

3. **Gestión de Roles**
    - Definición de puestos con rangos salariales base

4. **Historial Salarial**
    - Seguimiento de cambios de salario
    - Notificaciones automáticas

## 🏗️ Arquitectura y Patrones de Diseño

### 1. **Arquitectura Hexagonal (Puertos y Adaptadores)**
El sistema sigue una arquitectura hexagonal que separa:
- **Dominio**: Lógica de negocio pura
- **Aplicación**: Casos de uso y orquestación
- **Infraestructura**: Acceso a datos, APIs externas

### 2. **Patrones Implementados**

#### **a) Strategy Pattern**
**Ubicación**: `domain.strategy.*`
- **Propósito**: Manejar diferentes algoritmos de cálculo de salario
- **Implementación**:
    - `FixedSalaryStrategy`: Salario fijo mensual
    - `HourlySalaryStrategy`: Salario por horas
    - `CommissionSalaryStrategy`: Salario base + comisiones
- **Factory**: `SalaryStrategyFactory` crea la estrategia según tipo de empleado

#### **b) Adapter Pattern**
**Ubicación**: `infrastructure.adapter.*`
- **Propósito**: Adaptar interfaces del dominio a implementaciones de infraestructura
- **Ejemplos**:
    - `EmployeeAdapter`: Adapta repositorios JPA a servicios del dominio
    - `RoleAdapter`: Conecta servicios de roles con la capa de aplicación
    - `SalaryAdapter`: Gestiona el historial salarial

#### **c) Decorator Pattern**
**Ubicación**: `domain.decorator.*`
- **Propósito**: Añadir funcionalidades de forma dinámica
- **Implementación**:
    - `BaseSalaryCalculator`: Cálculo base
    - `SeniorityBonusDecorator`: Añade bonificación por antigüedad
    - `PerformanceBonusDecorator`: Añade bonificación por desempeño

#### **d) Observer Pattern**
**Ubicación**: `domain.observer.*`
- **Propósito**: Notificar cambios en salarios a múltiples componentes
- **Implementación**:
    - `SalarySubject`: Mantiene lista de observadores
    - `EmailNotificationObserver`: Envía notificaciones por email
    - `LoggingObserver`: Registra cambios en logs

#### **e) Factory Pattern**
**Ubicación**: `domain.factory.*`
- **Propósito**: Crear empleados con configuraciones específicas
- **Implementación**: `EmployeeFactory` crea empleados según tipo

## ✅ Principios SOLID Aplicados

### 1. **Single Responsibility Principle (SRP)**
- Cada clase tiene una única responsabilidad:
    - `EmployeeAdapter`: Solo adapta entre dominio e infraestructura
    - `FixedSalaryStrategy`: Solo calcula salarios fijos
    - `SalarySubject`: Solo gestiona observadores

### 2. **Open/Closed Principle (OCP)**
- **Abierto para extensión**: Nuevas estrategias de salario pueden añadirse sin modificar código existente
- **Cerrado para modificación**: `SalaryStrategyFactory` no necesita cambios para nuevas estrategias
- Ejemplo: Añadir `ContractorSalaryStrategy` solo requiere crear la clase e implementar la interfaz

### 3. **Liskov Substitution Principle (LSP)**
- Todas las estrategias implementan `SalaryStrategyService` y son intercambiables
- Los decoradores extienden `BonusDecorator` y pueden sustituir a `SalaryCalculator`

### 4. **Interface Segregation Principle (ISP)**
- Interfaces pequeñas y específicas:
    - `EmployeeGetService`: Solo operaciones de lectura
    - `EmployeeSaveService`: Solo operaciones de guardado
    - `EmployeeUpdateService`: Solo operaciones de actualización

### 5. **Dependency Inversion Principle (DIP)**
- Dependencias en interfaces, no implementaciones:
    - `CalculateEmployeeSalaryUseCase` depende de `EmployeeGetService` (interfaz)
    - `SalaryAdapter` depende de `SalaryGetService`, `SalarySaveService` (interfaces)
    - Inyección de dependencias vía constructor

## 📁 Estructura del Proyecto

```
com.empleados.patrones_diseno/
├── application/              # Casos de uso y servicios de aplicación
│   ├── useCase/             # Casos de uso específicos
│   └── *.java               # Servicios de aplicación
├── config/                  # Configuración Spring
├── domain/                  # Lógica de negocio pura
│   ├── decorator/           # Patrón Decorator
│   ├── entities/            # Entidades del dominio
│   ├── factory/             # Patrón Factory
│   ├── observer/            # Patrón Observer
│   ├── services/            # Interfaces de servicios
│   └── strategy/            # Patrón Strategy
├── infrastructure/          # Implementaciones técnicas
│   ├── adapter/             # Patrón Adapter
│   ├── dto/                 # Objetos de transferencia
│   ├── mapper/              # Mapeadores
│   └── repository/          # Repositorios JPA
└── repository/              # Interfaces de repositorio
```

## 🔧 Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MapStruct** (mapeo objeto-objeto)
- **Lombok** (reducción de código boilerplate)
- **H2/MySQL/PostgreSQL** (según configuración)
- **Maven/Gradle** (gestión de dependencias)

## 🚀 Cómo Ejecutar

1. **Requisitos previos**:
   ```bash
   Java 17 o superior
   Maven 3.6+
   Base de datos configurada
   ```

2. **Configuración**:
   ```properties
   # application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/empleados_db
   spring.datasource.username=usuario
   spring.datasource.password=contraseña
   ```

3. **Ejecución**:
   ```bash
   mvn spring-boot:run
   ```

## 🧪 Ejemplos de Uso

### Calcular Salario
```java
// Salario base
BigDecimal salario = calculateEmployeeSalaryUseCase.execute(1, 12, false);

// Salario con bonificaciones
BigDecimal salarioConBonus = calculateEmployeeSalaryUseCase.execute(1, 12, true);
```

### Crear Empleado
```java
Employee empleado = EmployeeFactory.createEmployee(
    EmployeeType.FIJO,
    "Juan",
    "Pérez",
    role
);
employeeSave.saveEmployee(empleado);
```

## 📊 Diagramas de Clases (Resumen)

```
Strategy Pattern:
SalaryUpdateService.SalaryStrategyService
        ↑
    implements
        ↑
FixedSalaryStrategy   HourlySalaryStrategy   CommissionSalaryStrategy
        ↑
    uses
        ↑
SalaryStrategyFactory

Decorator Pattern:
SalaryCalculator
        ↑
    implements
        ↑
BaseSalaryCalculator
        ↑
    extends
        ↑
BonusDecorator
        ↑
    extends
        ↑
SeniorityBonusDecorator   PerformanceBonusDecorator
```

## 🎯 Ventajas de la Implementación

1. **Mantenibilidad**: Código modular y fácil de modificar
2. **Escalabilidad**: Nuevas funcionalidades se añaden sin afectar existentes
3. **Testeabilidad**: Dependencias inyectadas facilitan pruebas unitarias
4. **Flexibilidad**: Patrones permiten cambios de comportamiento en runtime
5. **Separación de preocupaciones**: Cada capa tiene responsabilidad clara

## 🔮 Extensiones Futuras

1. **Nuevas estrategias de salario**: `FreelanceSalaryStrategy`
2. **Nuevos observadores**: `SMSNotificationObserver`
3. **Nuevos decoradores**: `ProjectBonusDecorator`
4. **Integración con sistemas externos**: ERP, nómina electrónica
5. **API REST**: Exponer funcionalidades como microservicios

