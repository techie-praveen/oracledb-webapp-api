package oracledb.webapp.api.config;

/**
 * API Constants for Employee and Department management
 */
public class ApiConstants {

    // API Base Paths
    public static final String API_BASE_PATH = "/api";
    public static final String DEPT_ENDPOINT = "/departments";
    public static final String EMP_ENDPOINT = "/employees";

    // Response Messages
    public static final String DEPT_CREATED = "Department created successfully";
    public static final String DEPT_UPDATED = "Department updated successfully";
    public static final String DEPT_DELETED = "Department deleted successfully";
    public static final String DEPT_NOT_FOUND = "Department not found";

    public static final String EMP_CREATED = "Employee created successfully";
    public static final String EMP_UPDATED = "Employee updated successfully";
    public static final String EMP_DELETED = "Employee deleted successfully";
    public static final String EMP_NOT_FOUND = "Employee not found";

    // Validation Messages
    public static final String DEPT_NAME_REQUIRED = "Department name is required";
    public static final String EMP_NAME_REQUIRED = "Employee name is required";
    public static final String INVALID_EMAIL = "Invalid email format";
    public static final String DEPT_ID_REQUIRED = "Department ID is required for employee";

    // HTTP Status Messages
    public static final String ERROR_INTERNAL = "Internal server error";
    public static final String ERROR_NOT_FOUND = "Resource not found";
    public static final String ERROR_BAD_REQUEST = "Bad request";

    private ApiConstants() {
        // Private constructor to prevent instantiation
    }
}

