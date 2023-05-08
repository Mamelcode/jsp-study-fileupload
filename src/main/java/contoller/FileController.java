package contoller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/file")
public class FileController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
				
		String path = req.getServletContext().getRealPath("/upload");
		int fileSize = 1024 * 1024 * 5; // 5MB
		
		File f = new File(path);
		if(! f.exists()) {
			f.mkdirs();
		}
		
		System.out.println("path ==> "+ path);
		
		String filename = null;
		String orgfilename = null;
		
		try {
			MultipartRequest multi = new MultipartRequest(
						req,
						path,
						fileSize,
						"UTF-8",
						new DefaultFileRenamePolicy()
					);
			
			String name = multi.getParameter("name");
			System.out.println("name ==> "+ name);
			Enumeration e = multi.getFileNames();
			System.out.println("file ==> "+ e);
			
			while(e.hasMoreElements()) {
				String file = (String)e.nextElement();
				filename = multi.getFilesystemName(file);
				System.out.println("filename ==> "+ filename);
				orgfilename = multi.getOriginalFileName(file);
				System.out.println("orgfilename ==> "+ orgfilename);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("imgPath", path);
		req.setAttribute("imgName", filename);
		
		req.getRequestDispatcher("/WEB-INF/views/file.jsp").forward(req, resp);
	}
}
