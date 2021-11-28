package swu.edu.hzd;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="Upload")
public class Upload extends HttpServlet {
    private static final String UPLOADDIRECOTRY = "upload";
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!ServletFileUpload.isMultipartContent(request)){
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
        int flag=0;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setHeaderEncoding("utf-8");
        String uploadPath = request.getServletContext().getRealPath("./")+File.separator + UPLOADDIRECOTRY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try{
            List<FileItem> formItems = upload.parseRequest(request);
            if(formItems!=null&&formItems.size()>0){
                for(FileItem file:formItems){
                    if(!file.isFormField()){
                        String fileName = new File(file.getName()).getName();
                        String filePath = uploadPath+File.separator+fileName;
                        File storeFile = new File(filePath);
                        System.out.println(filePath);
                        file.write(storeFile);
                        flag = 1;
                    }
                }
            }
        }catch (Exception ex){
            flag = -1;
        }

        response.sendRedirect("Add.html?success="+flag);
    }

    protected  void doGet(HttpServletRequest request,HttpServletResponse response){


    }
}
