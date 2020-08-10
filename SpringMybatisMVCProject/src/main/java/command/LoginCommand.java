package command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCommand {
	private String userId;
	private Boolean idStore;
	private Boolean autoLogin;
	private String userPw;
}
