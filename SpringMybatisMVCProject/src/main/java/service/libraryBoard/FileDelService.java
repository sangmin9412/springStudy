package service.libraryBoard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import command.FileInfo;

@Service
public class FileDelService {

	public void fileAdd(FileInfo fileInfo, HttpSession session, Model model) {
		Integer num = 0;
		Boolean newFile = true;
		List<FileInfo> list = (List<FileInfo>) session.getAttribute("fileList");
		
		if (list == null) {
			list = new ArrayList<FileInfo>();
		}
		
		
		// session이 있는 경우 삭제취소
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStoreFileName().equals(fileInfo.getStoreFileName())) {
				list.remove(i);
				newFile = false;
				num = 0;
			}
		}
		
		// 삭제
		if (newFile) {
			list.add(fileInfo);
			num = 1;
		}
		
		System.out.println(list.size());
		
		model.addAttribute("num", num);
		session.setAttribute("fileList", list);
	}
	
}
