package hust.admin.project.Model;

import java.util.List;

import hust.admin.project.Entity.User;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GroupUserModel {
	 private Long id;
	    private String group_name;
	    private String group_code;
	    private Long status;
	    private List<User> user;

}
