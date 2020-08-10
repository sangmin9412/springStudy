package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import command.MemberCommand;

public class MemberModifyProVaildator implements Validator {
	// �씠硫붿씪 �뙣�꽩
	private static final String emailRegExp =
			"^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
	private Pattern pattern; // �뙣�꽩媛앹껜 �깮�꽦�븯湲� �쐞�빐 �꽑�뼵
	public MemberModifyProVaildator() {
		// �씠硫붿씪 �뙣�꽩 媛앹껜 �깮�꽦
		pattern = Pattern.compile(emailRegExp);
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		MemberCommand regReq = (MemberCommand)target;
		if(regReq.getUserEmail() == null || 
				regReq.getUserEmail().trim().isEmpty()) {
			errors.rejectValue("userEmail", "required"); 
		}else {
			Matcher matcher = pattern.matcher(regReq.getUserEmail());
			if(!matcher.matches()) {
				errors.rejectValue("userEmail","bad");
			}
		}
		ValidationUtils.rejectIfEmpty(errors, "userPw", "required");
		ValidationUtils.rejectIfEmpty(errors, "userPh1", "required");
		ValidationUtils.rejectIfEmpty(errors, "userAddr", "required");
	}
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
}
