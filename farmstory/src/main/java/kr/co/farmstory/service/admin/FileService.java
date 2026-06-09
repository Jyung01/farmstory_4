package kr.co.farmstory.service.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import kr.co.farmstory.dto.admin.FileDTO;

public enum FileService {
	INSTANCE;
	
	public List<FileDTO> upload(HttpServletRequest req) {
		List<FileDTO> dtoList = new ArrayList<>();
		
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
		
		File directory = new File(path);
		
		if(!directory.exists()) {
			directory.mkdir();
		}
		
		try {
			Collection<Part> parts = req.getParts();
			
			for (Part part : parts) {
				String ofName = part.getSubmittedFileName();
			
				if(ofName != null && !ofName.isEmpty()) {
					int index = ofName.lastIndexOf(".");
					String ext = ofName.substring(index); 
					
					String sfName= UUID.randomUUID().toString() + ext;
					
					part.write(path + File.separator + sfName);
					
					FileDTO dto = new FileDTO();
					dto.setOfname(ofName);
					dto.setSfname(sfName);
					dtoList.add(dto);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
}
