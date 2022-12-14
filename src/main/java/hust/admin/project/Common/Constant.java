package hust.admin.project.Common;

public class Constant {
	public static final class STATUS {
		public static final Long ACTIVE = 1L;
		public static final Long DE_ACTIVE = 0L;
		public static final Long DELETED = -2L;
		public static final Long SIGNUP = -1L;
	}

	public static final class RESULT_CODE {
		public static final Long SUCCESS = 0L;
		public static final Long ERROR = -1L;
		public static final Long NOT_FOUND = 404L;
		public static final Long NOT_ALLOWED = 405L;
		public static final Long BAN_USER = -2L;
		public static final Long INTERNAL_ERROR = 500L; 
		public static final Long ACCESS_DENIED = 403L;

	}

	public static final class API {

		public static final String PREFIX = "/api/v1/project";
		public static final String PREFIX_AUTH = "/api/v1/project/auth";
	}

	public static final class MESSAGE {
		public static final String SUCCESS = "Successfully";
		public static final String ERROR = "Error";
		public static final String NOT_FOUND_USER = "Not found user in system";
		public static final String PASSWORD_INCORRECT = "Password incorrect";
		public static final String USERNAME_EXIST = "Username already exist";
		public static final String ACCOUNT_USED = "Account is used by other people";
		public static final String NOT_FOUND_HANDLE = "Not found api";
		public static final String NOT_ALLOWED = "Method not allowed";
		public static final String BAN_USER = "User is banned";
		public static final String INTERNAL_ERROR = "INTERNAL SERVER ERROR";
		public static final String DATA_NULL = "Data is null";
		public static final String ACTIVE = "Active";
		public static final String DEACTIVE = "Deactive";
		public static final String DENIED = "Access denied";
	}

	public static final class ROLE {
		public static final String EMPLOYEE = "ROLE_EMPLOYEE";
		public static final String MANAGER = "ROLE_MANAGER";
		public static final String ADMIN = "ROLE_ADMIN";
	}

	public static final class PERMISSION {
		public static final String READ = "READ";
		public static final String UPDATE = "UPDATE";
		public static final String DELETE = "DELETE";
		public static final String CREATE = "CREATE";

	}
	public static final class NAME {
		public static final String APPLICATION = "APPLICATION";
		public static final String GROUP = "GROUP";
		public static final String LOG = "LOG";
		public static final String IP_ADRESS = "IP_ADRESS";
		public static final String SCHEDULE = "SCHEDULE";
		public static final String USER = "USER";
 		
	}
	public static final class ACTION {
		public static final String LOGIN = "Login";
		
	}
}