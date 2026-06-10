package kr.co.farmstory.controller.article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import kr.co.farmstory.dto.article.ArticleDTO;
import kr.co.farmstory.dto.article.FileDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.article.ArticleService;
import kr.co.farmstory.service.article.FileService;
import kr.co.farmstory.util.ArticleSwitch;
import kr.co.farmstory.util.FileUpload;

@WebServlet("/article/write.do")
public class WriteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	ArticleService service = ArticleService.INSTANCE;
	FileService fileService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
	    UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		// 파라미터 값 저장
		String groupName = req.getParameter("groupName");
		String cate = req.getParameter("cate");
		
		req.setAttribute("groupName", groupName);
		req.setAttribute("cate", cate);
		req.setAttribute("groupTitle", ArticleSwitch.GROUP_TITLE.get(groupName));
		req.setAttribute("cateTitle", ArticleSwitch.CATE_TITLE.get(cate));
		req.setAttribute("navImage", ArticleSwitch.NAV_IMAGE.get(cate));
		
		// notice 게시판에서 관리자인지 확인
		if("notice".equals(cate) && !"admin".equals(sessUser.getRole())) {
		    resp.sendRedirect(req.getContextPath()
		            + "/article/list.do?groupName=" + groupName
		            + "&cate=" + cate);
		    return;
		}
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/article/write.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
	    UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
	    
	    // 등록한 파일 가져오기
	    Collection<Part> parts = req.getParts();
	    List<FileDTO> fileList = new ArrayList<>();
	    int fileCount = 0;
	    
	    for(Part part : parts){

	        if(part.getName().equals("file") && part.getSize() > 0){

	            fileCount++;

	            String oriName = part.getSubmittedFileName();

	            String newName = FileUpload.uploadFile(req, part);

	            FileDTO fileDTO = new FileDTO();

	            fileDTO.setOfname(oriName);
	            fileDTO.setSfname(newName);

	            fileList.add(fileDTO);
	        }
	    }
	    
	    // 파라미터 값 저장
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String groupName = req.getParameter("groupName");
		String cate = req.getParameter("cate");
		String regip = req.getRemoteAddr();
		
		// notice 게시판에서 관리자인지 확인
		if("notice".equals(cate) && !"admin".equals(sessUser.getRole())) {
		    resp.sendRedirect(req.getContextPath()
		            + "/article/list.do?groupName=" + groupName
		            + "&cate=" + cate);
		    return;
		}
	    
	    // 게시판 테이블에 저장하기위해 dto 생성
	    ArticleDTO dto = new ArticleDTO();
		dto.setGroupName(groupName);
	    dto.setCate(cate);
	    dto.setTitle(title);
	    dto.setContent(content);
	    dto.setWriter(sessUser.getUserid());
	    dto.setRegip(regip);
	    dto.setFileCount(fileCount);
	    
	    
	    // 게시판 등록 서비스 요청
	    int ano = service.register(dto);
	    
	    // 파일 등록 서비스 요청
	    for (FileDTO fileDTO : fileList) {
	    	fileDTO.setAno(ano);
	    	fileService.register(fileDTO);
	    }
	    
	    
		// 리다이렉트
	    resp.sendRedirect(req.getContextPath()
	            + "/article/list.do?groupName="
	            + groupName
	            + "&cate="
	            + cate);
		
		
	}
}
