package command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCommand {
	String urlPath;
	String userId;
	String userPw;
	String userPwCon;
	String userName;
	String oldPw;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	// @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	// @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	Date userBirth;
	String userGender;
	String userEmail;
	String userAddr;
	String userPh1;
	String userPh2;
	String [] interest;
	
	public boolean isUserPwEqualToUserPwCon() {
		if(userPw.equals(userPwCon)) {
			return true;
		}
		return false;
	}
	
}
