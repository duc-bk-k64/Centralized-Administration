package hust.admin.project.Controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hust.admin.project.Common.Constant;
import hust.admin.project.Configuration.JwtConfig.JwtProvider;
import hust.admin.project.Entity.Account;
import hust.admin.project.Entity.ManageToken;
import hust.admin.project.Entity.Permission;
import hust.admin.project.Entity.Role;
import hust.admin.project.Model.AccountModel;
import hust.admin.project.Model.ResponMessage;
import hust.admin.project.Model.RoleModel;
import hust.admin.project.Model.SignInData;
import hust.admin.project.Repository.AccountRepository;
import hust.admin.project.Repository.ManageTokenRepository;
import hust.admin.project.Repository.PermissionRepository;
import hust.admin.project.Repository.RoleRepository;

@RestController
@RequestMapping(Constant.API.PREFIX)
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ManageTokenRepository manageTokenRepository;

	@PostMapping("/createAccount")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponMessage signup(@RequestBody SignInData signUp) {
		ResponMessage responMessage = new ResponMessage();

		if (accountRepository.existsByUsername(signUp.getUserName())) {
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setMessage(Constant.MESSAGE.USERNAME_EXIST);
			return responMessage;

		} else {
			try {
				Account account = new Account();
				account.setStatus(Constant.STATUS.ACTIVE);
				account.setUsername(signUp.getUserName());
				account.setPassword(passwordEncoder.encode(signUp.getPassWord()));
				Set<Role> roles = new HashSet<>();
				Role role = roleRepository.findByName(Constant.ROLE.EMPLOYEE);
				roles.add(role);
				Set<Permission> permissions = new HashSet<>();
				Permission permission = permissionRepository.findByName(Constant.PERMISSION.READ);
				permissions.add(permission);
				account.setRoles(roles);
				account.setPermissions(permissions);
				account.setEmail(signUp.getEmail());
				accountRepository.save(account);
				responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
				responMessage.setMessage(Constant.MESSAGE.SUCCESS);
				responMessage.setData(account);
				return responMessage;
			} catch (Exception e) {
				responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
				responMessage.setMessage(Constant.MESSAGE.ERROR);
				responMessage.setData(e.getMessage());
				return responMessage;
			}
		}

	}

	@PutMapping("/updateAccount")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public ResponMessage updateAccount(@RequestBody SignInData data) {
		ResponMessage responMessage = new ResponMessage();
		try {
			Account account = accountRepository.findUserByUsername(data.getUserName());
			if (data.getPassWord() != null)
				account.setPassword(passwordEncoder.encode(data.getPassWord()));
			if (data.getEmail() != null)
				account.setEmail(data.getEmail());
			if (data.getStatus() != null)
				account.setStatus(data.getStatus());
			if (data.getRole().size() > 0) {
				Set<Role> roles = new HashSet<>();
				data.getRole().forEach(role -> {
					switch (role) {
					case Constant.ROLE.ADMIN:
						Role dataRole = roleRepository.findByName(Constant.ROLE.ADMIN);
						roles.add(dataRole);
						break;

					case Constant.ROLE.MANAGER:
						Role dataRoleM = roleRepository.findByName(Constant.ROLE.MANAGER);
						roles.add(dataRoleM);
						break;
					case Constant.ROLE.EMPLOYEE:
						Role dataRoleE = roleRepository.findByName(Constant.ROLE.EMPLOYEE);
						roles.add(dataRoleE);
						break;
					}

				});
				Set<Permission> permissions = new HashSet<>();
				data.getRole().forEach(per -> {
					switch (per) {
					case Constant.PERMISSION.CREATE:
						Permission dataC = permissionRepository.findByName(Constant.PERMISSION.CREATE);
						permissions.add(dataC);
						break;
					case Constant.PERMISSION.DELETE:
						Permission permission = permissionRepository.findByName(Constant.PERMISSION.DELETE);
						permissions.add(permission);
						break;
					case Constant.PERMISSION.UPDATE:
						Permission permissionUpdate = permissionRepository.findByName(Constant.PERMISSION.UPDATE);
						permissions.add(permissionUpdate);
						break;
					case Constant.PERMISSION.READ:
						Permission permission2 = permissionRepository.findByName(Constant.PERMISSION.READ);
						permissions.add(permission2);
						break;
					}

				});
				account.setRoles(roles);
				account.setPermissions(permissions);
			}

			accountRepository.save(account);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setData(account);

		} catch (Exception e) {
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}

	@PostMapping("/logout")
	@ResponseBody
	public ResponMessage logout(@RequestParam String token) {
		ResponMessage responMessage = new ResponMessage();
		ManageToken manageToken = new hust.admin.project.Entity.ManageToken();
		manageToken.setTime_create(Instant.now());
		manageToken.setToken(token);
		try {
			manageTokenRepository.removeTokenExprire(JwtProvider.time_expire / 1000);
			manageTokenRepository.save(manageToken);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setData(manageToken);

		} catch (Exception e) {
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}

	@PostMapping("/addRole")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public ResponMessage addRole(@RequestBody RoleModel roleModel) {
		ResponMessage responMessage = new ResponMessage();
		try {
			Account account = accountRepository.findUserByUsername(roleModel.getUsername());
			if (account == null) {
				responMessage.setMessage(Constant.MESSAGE.NOT_FOUND_USER);
				responMessage.setResultCode(Constant.RESULT_CODE.NOT_FOUND);
				return responMessage;
			}
			{
				Set<Role> roles = account.getRoles();
				roleModel.getRole().forEach(role -> {
					switch (role) {
					case Constant.ROLE.ADMIN:
						Role data = roleRepository.findByName(Constant.ROLE.ADMIN);
						if (!roles.contains(data))
							roles.add(data);
						break;

					case Constant.ROLE.MANAGER:
						Role dataRole = roleRepository.findByName(Constant.ROLE.MANAGER);
						if (!roles.contains(dataRole))
							roles.add(dataRole);
						break;
					}
				});
				account.setRoles(roles);
				Set<Permission> permissions = account.getPermissions();
				roleModel.getRole().forEach(per -> {
					switch (per) {
					case Constant.PERMISSION.CREATE:
						Permission data = permissionRepository.findByName(Constant.PERMISSION.CREATE);
//						System.out.println(data.getName());
						if (!permissions.contains(data))
							permissions.add(data);
						break;

					case Constant.PERMISSION.DELETE:
						Permission permission = permissionRepository.findByName(Constant.PERMISSION.DELETE);
//						System.out.println(permission.getName());
						if (!permissions.contains(permission))
							permissions.add(permission);
						break;
					case Constant.PERMISSION.UPDATE:
						Permission permissionUpdate = permissionRepository.findByName(Constant.PERMISSION.UPDATE);
//						System.out.println(permissionUpdate.getName());
						if (!permissions.contains(permissionUpdate))
							permissions.add(permissionUpdate);
						break;
					}

				});
				account.setPermissions(permissions);
				accountRepository.save(account);
				responMessage.setMessage(Constant.MESSAGE.SUCCESS);
				responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
				responMessage.setData(account);
			}
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}

	@GetMapping("/allAccount")
	@ResponseBody
	public ResponMessage findAllAccount() {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			List<Account> accounts = accountRepository.findAll();
			List<AccountModel> list = new ArrayList();
			accounts.forEach(account -> {
				AccountModel accountModel = new AccountModel();
				accountModel.setId(account.getId());
				accountModel.setUsername(account.getUsername());
				accountModel.setStatus(account.getStatus());
				List<String> roleList = new ArrayList();
				List<String> permissiomList = new ArrayList();
				account.getRoles().forEach(role -> {
					roleList.add(role.getName());
				});
				accountModel.setRole(roleList);
				account.getPermissions().forEach(per -> {
					permissiomList.add(per.getName());
				});
				accountModel.setPermission(permissiomList);

				list.add(accountModel);
			});
			responMessage.setData(list);
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}

	@GetMapping("/role")
	@ResponseBody
	public ResponMessage getAllRole() {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(roleRepository.findAllRole());
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}

	@GetMapping("/permission")
	@ResponseBody
	public ResponMessage getAllPermission() {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(permissionRepository.findAllPermissions());
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}

	@PostMapping("/deleteAccountMap")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public ResponMessage deleteAccountMap(@RequestParam String username) {
		ResponMessage responMessage = new ResponMessage();
		try {
			Account account = accountRepository.findUserByUsername(username);
			account.setRoles(null);
			account.setPermissions(null);
			accountRepository.save(account);
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}

	@DeleteMapping("/deleteAccount")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public ResponMessage deleteAccount(@RequestParam String username) {
		ResponMessage responMessage = new ResponMessage();
		try {
			Account account = accountRepository.findUserByUsername(username);
			accountRepository.delete(account);
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}

}
