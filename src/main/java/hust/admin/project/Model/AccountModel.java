package hust.admin.project.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountModel {
	private Long id;
	private String username;
	private Long status;
	private List<String> role;
	private List<String> permission;

}