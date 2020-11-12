package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.exception.ELoanException;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;



@WebServlet({ "/login","/registernewuser","/placeloan","/editloan"})
public class UserController extends HttpServlet {
private static final long serialVersionUID = 1L;


private static final String INS_APPLY_LOAN = null;


private static final String UPD_LOAN_REQ = null;


private static final String INS_NEW_USER = null;


private static final String GET_LOAN_INFO = null;


private ConnectionDao connDao;

public void setConnDao(ConnectionDao connDao) {
this.connDao = connDao;
}

User user = new User();
HttpSession session = null;

public void init(ServletConfig config) {
String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);

}

public void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
doGet(request, response);
}

public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

session = request.getSession();
String action = request.getServletPath();
String viewName = "";
try {
switch (action) {
case "/registernewuser":
viewName=registernewuser(request,response);
break;
case "/login":
user = new User();
user.setUsername(request.getParameter("loginid"));
user.setPassword(request.getParameter("password"));
session.setAttribute("user", user);
break;
case "/validate":
viewName=validate(request,response);
break;
case "/placeloan":
viewName=placeloan(request,response);
break;
case "/application1":
viewName=application1(request,response);
break;
case "/editLoanProcess"  :
viewName=editLoanProcess(request,response);
break;
case "/registeruser":
viewName=registerUser(request,response);
break;
case "/register":
viewName = register(request, response);
break;
case "/application":
viewName = application(request, response);
break;
case "/trackloan":
viewName = trackloan(request, response);
break;
case "/editloan":
viewName = editloan(request, response);
break;
case  "/displaystatus" :
viewName=displaystatus(request,response);
break;
case  "/logout" :
viewName="index.jsp";
session.removeAttribute("user");
break;
default : viewName = "notfound.jsp"; break;
}
} catch (Exception ex) {

throw new ServletException(ex.getMessage());
}
RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
dispatch.forward(request, response);
}

private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
/* write the code to validate the user */

return null;
}
private String placeloan(HttpServletRequest request, HttpServletResponse response) throws ELoanException {
LoanInfo newLoan = new LoanInfo();
String vw;
newLoan.setPurpose(request.getParameter("purpose"));
newLoan.setAmtrequest(Integer.parseInt(request.getParameter("amtRequested")));
newLoan.setBstructure(request.getParameter("bstructure"));
newLoan.setBindicator(request.getParameter("bindicator"));
newLoan.setMobile(request.getParameter("mobile"));
newLoan.setEmail(request.getParameter("email"));
newLoan.setAddress(request.getParameter("address"));

try{
Connection con = ConnectionDao.connect();
PreparedStatement ps = con.prepareStatement(INS_APPLY_LOAN);
ps.setString(1, user.getUsername());
ps.setString(2, request.getParameter("loanName"));
ps.setInt(3, newLoan.getAmtrequest());
ps.setString(4, newLoan.getBstructure());
ps.setString(5, newLoan.getBindicator());
ps.setString(6, newLoan.getMobile());
ps.setString(7, newLoan.getAddress());
ps.setString(8, newLoan.getPurpose());

ps.executeUpdate();
vw = "userhome1.jsp";

} catch (SQLException e) {
throw new ELoanException("SQL Exception: Loan request not submitted. Try again");
}

return vw;
}


private String application1(HttpServletRequest request, HttpServletResponse response) {
// TODO Auto-generated method stub
/* write the code to display the loan application page */

return null;
}
private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException, ELoanException {
/* write the code to edit the loan info */
LoanInfo editloano = new LoanInfo();
editloano.setPurpose(request.getParameter("purpose"));
editloano.setAmtrequest(Integer.parseInt(request.getParameter("amtrequest")));
editloano.setBstructure(request.getParameter("bstructure"));
editloano.setBindicator(request.getParameter("bindicator"));
editloano.setAddress(request.getParameter("address"));

try{
Connection con = ConnectionDao.connect();
PreparedStatement ps = con.prepareStatement(UPD_LOAN_REQ);

ps.setString(6, user.getUsername());
ps.setInt(1, editloano.getAmtrequest());
ps.setString(2, editloano.getAddress());
ps.setString(3, editloano.getPurpose());
ps.setString(4, editloano.getBstructure());
ps.setString(5, editloano.getBindicator());

ps.executeUpdate();
} catch (SQLException e) {
throw new ELoanException("SQL Exception: Unable to update loan details"); }

return "editloan.jsp";
}
private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
// TODO Auto-generated method stub
/* write the code to redirect page to read the user details */
return "newuserui.jsp";
}
private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
/* write the code to create the new user account read from user and return to index page */
User newUser = new User();

newUser.setUsername(request.getParameter("userName"));
newUser.setPassword(request.getParameter("password"));


try{
Connection con = ConnectionDao.connect();
PreparedStatement ps = con.prepareStatement(INS_NEW_USER);
ps.setString(1, newUser.getUsername());
ps.setString(5, newUser.getPassword());

ps.executeUpdate();

} catch (SQLException e) {
e.printStackTrace();
}


request.setAttribute("newUser", newUser);
//request.getRequestDispatcher("newuserui.jsp").forward(request, response);

return "newuserui.jsp";
}

private String register(HttpServletRequest request, HttpServletResponse response) {
// TODO Auto-generated method stub
/* write the code to redirect to register page */

return null;
}
private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
// TODO Auto-generated method stub
/* write the code the display the loan status based on the given application
  number
*/

return null;
}

private String editloan(HttpServletRequest request, HttpServletResponse response) throws ELoanException {
/* write a code to return to editloan page */

return null;
}

private String trackloan(HttpServletRequest request, HttpServletResponse response) throws ELoanException {
/* write a code to return to trackloan page */
return null;
}

private LoanInfo getLoanData() throws ELoanException {
LoanInfo loan = new LoanInfo();
try(Connection con = ConnectionDao.connect();
PreparedStatement pst = con.prepareStatement(GET_LOAN_INFO)){
pst.setString(1, user.getUsername());
ResultSet rs = pst.executeQuery();
if(rs.next()) {
loan.setApplno(rs.getString("LOAN_APPL_ID"));
loan.setStatus(rs.getString("STATUS"));
loan.setAmtrequest(rs.getInt("LOAN_AMOUNT"));
loan.setDoa(rs.getString("LOAN_APPL_DATE"));
loan.setMobile(rs.getString("MOBILE"));
loan.setAddress(rs.getString("ADDRESS"));
loan.setPurpose(rs.getString("PURPOSE"));
loan.setBindicator(rs.getString("LOAN_BILL_IND"));
loan.setBstructure(rs.getString("LOAN_BUS_STRC"));
}
session.setAttribute("loan", loan);
}catch(SQLException e){
throw new ELoanException("SQL Exception");
}
return loan;

}

private String application(HttpServletRequest request, HttpServletResponse response) {
// TODO Auto-generated method stub
/* write a code to return to trackloan page */
return null;
}
}
