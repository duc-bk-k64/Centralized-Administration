package hust.admin.project.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInData {
	private String userName;
	private String passWord;
	private String email;
	private List<String> role;
	private Long status;
}