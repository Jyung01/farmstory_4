package kr.co.farmstory.controller.article;

import java.io.File;
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
import kr.co.farmstory.dto.article.CommentDTO;
import kr.co.farmstory.dto.article.FileDTO;
import kr.co.farmstory.dto.page.PageGroupDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.article.ArticleService;
import kr.co.farmstory.service.article.CommentService;
import kr.co.farmstory.service.article.FileService;
import kr.co.farmstory.util.ArticleSwitch;
import kr.co.farmstory.util.FileUpload;

@WebServlet("/article/modify.do")
public class ModifyController extends HttpServlet {
	
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
		String page = req.getParameter("page");
		int ano = Integer.parseInt(req.getParameter("ano"));
		
		
		if(!service.isOwnerOrAdmin(ano, sessUser)) {
		    resp.sendRedirect(req.getContextPath()
		            + "/article/view.do?ano=" + ano
		            + "&groupName=" + groupName
		            + "&cate=" + cate
		            + "&page=" + page);
		    return;
		}
		
		ArticleDTO dto = service.findById(ano);
		List<FileDTO> fileList = fileService.findFiles(ano);
		
		req.setAttribute("fileList", fileList);
		req.setAttribute("dto", dto);
		
		req.setAttribute("groupName", groupName);
		req.setAttribute("cate", cate);
		req.setAttribute("page", page);
		
		req.setAttribute("groupTitle", ArticleSwitch.GROUP_TITLE.get(groupName));
        req.setAttribute("cateTitle", ArticleSwitch.CATE_TITLE.get(cate));
        req.setAttribute("navImage", ArticleSwitch.NAV_IMAGE.get(cate));
			
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/article/modify.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
	    UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

	    int ano = Integer.parseInt(req.getParameter("ano"));

	    String groupName = req.getParameter("groupName");
	    String cate = req.getParameter("cate");
	    String page = req.getParameter("page");
	    String title = req.getParameter("title");
	    String content = req.getParameter("content");
	    
	    if(!service.isOwnerOrAdmin(ano, sessUser)) {
	        resp.sendRedirect(req.getContextPath()
	                + "/article/view.do?ano=" + ano
	                + "&groupName=" + groupName
	                + "&cate=" + cate
	                + "&page=" + page);
	        return;
	    }
        
        // 삭제할 파일 리스트
        String[] deleteFiles = req.getParameterValues("deleteFile");
        
        // 등록한 파일 객체
        Collection<Part> parts = req.getParts();
        
        // 업로드 파일 리스트
        List<FileDTO> uploadList = new ArrayList<>();
        
        // 업로드한 파일 리스트에 담기
        for(Part part : parts) {

            if(part.getName().equals("file") && part.getSize() > 0) {

                String ofname = part.getSubmittedFileName();
                String sfname = FileUpload.uploadFile(req, part);

                FileDTO fileDTO = new FileDTO();
                fileDTO.setAno(ano);
                fileDTO.setOfname(ofname);
                fileDTO.setSfname(sfname);

                uploadList.add(fileDTO);
            }
        }
        
        // 원래 글에 있던 파일 갯수
        int currentCount = fileService.countFiles(ano);
        
        // 삭제한 파일 갯수 계산
        int deleteCount = deleteFiles == null ? 0 : deleteFiles.length;
        
        // 업로드 파일 갯수
        int uploadCount = uploadList.size();
        
        // 최종 업로드 파일 갯수
        int resultCount = currentCount - deleteCount + uploadCount;
        
        // 파일 2개 넘기면 다시 modify로 이동
        if(resultCount > 2) {

            ArticleDTO dto = new ArticleDTO();
            dto.setAno(ano);
            dto.setTitle(title);
            dto.setContent(content);

            List<FileDTO> fileList = fileService.findFiles(ano);

            req.setAttribute("msg", "첨부파일은 최대 2개까지 가능합니다.");
            req.setAttribute("dto", dto);
            req.setAttribute("fileList", fileList);

            req.setAttribute("groupName", groupName);
            req.setAttribute("cate", cate);
            req.setAttribute("page", page);

            req.setAttribute("groupTitle", ArticleSwitch.GROUP_TITLE.get(groupName));
            req.setAttribute("cateTitle", ArticleSwitch.CATE_TITLE.get(cate));
            req.setAttribute("navImage", ArticleSwitch.NAV_IMAGE.get(cate));

            req.getRequestDispatcher("/WEB-INF/views/article/modify.jsp").forward(req, resp);
            return;
        }
        
        ArticleDTO dto = new ArticleDTO();
        dto.setAno(ano);
        dto.setTitle(title);
        dto.setContent(content);
        
        // 글 수정 서비스 요청
        service.modify(dto);
        
        // 삭제할 파일이 있으면 업로드 폴더에서 파일 삭제
        if(deleteFiles != null) {

            for(String fnoStr : deleteFiles) {

                int fno = Integer.parseInt(fnoStr);

                FileDTO fileDTO = fileService.findFile(fno);

                if(fileDTO != null) {

                    File savedFile = new File(
                            req.getServletContext().getRealPath("/upload/article"),
                            fileDTO.getSfname());

                    if(savedFile.exists()) {
                        savedFile.delete();
                    }

                    fileService.remove(fno);
                }
            }
        }
        
        // 등록한 파일만큼 업로드
        for(FileDTO fileDTO : uploadList) {
            fileService.register(fileDTO);
        }
        
        // 파일 갯수 다시 계산해 업데이트
        int fileCount = fileService.countFiles(ano);
        service.updateFileCount(ano, fileCount);
        
        resp.sendRedirect(req.getContextPath()
                + "/article/view.do?ano=" + ano
                + "&groupName=" + groupName
                + "&cate=" + cate
                + "&page=" + page);
        
	}
}
