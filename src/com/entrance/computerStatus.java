package com.entrance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.run.util.CommandUtils;
import com.run.util.SystemMonitor;

/**
 * Servlet implementation class computerStatus
 */
@WebServlet("/computerStatus")
public class computerStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public computerStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String sss = request.getParameter("baowen");
		System.out.println(sss);
		StringBuilder sb=new StringBuilder();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map map = SystemMonitor.getSystem();
		
		String OsName=(String)map.get(CommandUtils.OsName);
		String TotalMemorySize=(String)map.get(CommandUtils.TotalMemorySize);
		String UsedMemory=(String)map.get(CommandUtils.UsedMemory);
		sb.append("<div>操作系统&nbsp;"+OsName+"</div>");
		sb.append("<div>内存大小&nbsp;"+TotalMemorySize+"</div>");
		sb.append("<div>已使用&nbsp;"+UsedMemory+"</div>");
		
		sb.append("<br><div>磁盘使用</div>");
		ArrayList<String> list=(ArrayList<String>)map.get(CommandUtils.SystemList);
		for (String str : list) {
			sb.append("<div>"+str+"</div>");
		}

		//String sss = new String(sb.toString().getBytes("iso-8859-1"),"UTF-8");
		response.getWriter().append(sb.toString());
//		}
	}

}
