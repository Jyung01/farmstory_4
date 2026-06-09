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
import kr.co.farmstory.dto.article.ArticleDTO;
import kr.co.farmstory.dto.article.FileDTO;
import kr.co.farmstory.dto.page.PageGroupDTO;
import kr.co.farmstory.service.article.ArticleService;
import kr.co.farmstory.service.article.FileService;
import kr.co.farmstory.util.ArticleSwitch;

@WebServlet("/article/download.do")
public class DownloadController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	FileService fileService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int fno = Integer.parseInt(req.getParameter("fno"));

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

        fileService.updateDownload(fno);

        String encodedName = URLEncoder.encode(dto.getOfname(), "UTF-8").replaceAll("\\+", "%20");

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + encodedName + "\"");
        resp.setHeader("Content-Length", String.valueOf(file.length()));

        try (
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
        ) {
            byte[] buffer = new byte[1024];

            int read;

            while((read = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, read);
            }

            bos.flush();
        }
		
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/article/view.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
