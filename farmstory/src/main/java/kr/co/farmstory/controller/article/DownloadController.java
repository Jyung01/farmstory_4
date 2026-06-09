package kr.co.farmstory.controller.article;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.article.FileDTO;
import kr.co.farmstory.service.article.FileService;

@WebServlet("/article/download.do")
public class DownloadController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	FileService fileService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 다운로드할 파일 번호 받기
		int fno = Integer.parseInt(req.getParameter("fno"));
		
		// db에서 파일 정보 dto 받기
        FileDTO dto = fileService.findFile(fno);


        if(dto == null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }

        String path = req.getServletContext().getRealPath("/upload/article");
        File file = new File(path, dto.getSfname());

        if(!file.exists()) {
            resp.sendRedirect(req.getContextPath());
            return;
        }

        
        // 파일 다운로드 증가
        fileService.updateDownload(fno);

        String encodedName = URLEncoder.encode(dto.getOfname(), "UTF-8").replaceAll("\\+", "%20");
        
        
        // 다운로드 파일 설정 헤더
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + encodedName + "\"");
        resp.setHeader("Content-Length", String.valueOf(file.length()));

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(resp.getOutputStream());
            byte[] buffer = new byte[1024];
            int read;

            while((read = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, read);
            }

            bos.flush();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
        	if(bis != null) bis.close();
        	if(bos != null) bos.close();
        }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
