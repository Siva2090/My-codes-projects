package com.jspiders.programs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Homepage {
	
		private static void reservationRoom(Connection con, Scanner scn) {
			try {
				System.out.println("Enter guest name ");
				String guest = scn.next();
				System.out.println("enter the room number ");
				String roomno = scn.next();
				System.out.println("enter the contact number ");
				String number = scn.next();

				String query = "INSERT INTO reservation(GUESTNAME, ROOMNO, CONTACT) VALUES('" + guest + "','" + roomno + "','" + number + "')";
				Statement stmt = con.createStatement();
				int count = stmt.executeUpdate(query);
				
				if (count > 0) {
					System.out.println("Reservation successful.");
				} else {
					System.out.println("Reservation failed. Please try again.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		private static boolean reservationExists(Connection con,int id) {
			try {
				String query="SELECT ID FROM RESERVATION WHERE ID="+id;
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				return rs.next();
			}catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		private static void viewReservations(Connection con) {
			String query="SELECT ID,GUESTNAME,ROOMNO,CONTACT,DATE FROM RESERVATION";
			try {
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(query);
				System.out.println("   --- CURRENT RESERVATION ---");
				System.out.println("+-----------------+--------------------+-----------------+--------------------+-----------------------+");
				System.out.println("|     ID          |     GUEST_NAME     |     ROOM_NO     |     CONTACT_NO     |     DATE     ");
				System.out.println("+-----------------+--------------------+-----------------+--------------------+-----------------------+");
				while(rs.next()) {
					int id=rs.getInt("ID");
					String name = rs.getString("GUESTNAME");
					String room = rs.getString("roomno");
					String contact = rs.getString("contact");
					String date = rs.getTimestamp("date").toString();
					System.out.printf("| %-15d | %-18s | %-15s | %-18s | %12s |\n", id,name,room,contact,date);
				}
				System.out.println("+-----------------+--------------------+-----------------+--------------------+-----------------------+");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//create method for get room_no
		private static void getRoomNumber(Connection con,Scanner scn) {
			try {
				System.out.println("Enter Reservation Id : ");
				int id = scn.nextInt();
				System.out.println("Enter Guest Name : ");
				String name = scn.next();
				String query ="SELECT ROOMNO FROM RESERVATION WHERE ID="+id+" AND GUESTNAME ='"+name+"' ";
				Statement stmt = con.createStatement();
				ResultSet rs= stmt.executeQuery(query);
				if(rs.next()) {
					int room = rs.getInt("ROOMNO");
					System.out.println("ROOMNO : "+room);
					System.out.println("     Room Number For Above Given Id : "+id+" & NAME : "+name);
				}else {
					System.out.println("   --- RESERVATION  not found ---   ");
					System.out.println("       Reservation does not exit for given id : "+id+" & NAME : "+name);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		private static void updateReservations(Connection con,Scanner scn) {
			try {
				System.out.println("Enter Reservation ID to upate : ");
				int id = scn.nextInt();
				scn.nextLine();//for next line
				if(!reservationExists(con , id)) {
					System.out.println("   --- RESERVATION NOT FOUND FOR GIVEN ID ---   ");
					return ; 
				}
				System.out.println("Enter New Name for Guest : ");
				String name = scn.nextLine();
				System.out.println("Enter New room for Guest : ");
				int room =scn.nextInt();
				System.out.println("Enter New Contact Number of an Guest : ");
				String contact = scn.next();
				String query ="UPDATE RESERVATION SET GUESTNAME='"+name+"',ROOMNO="+room+",CONTACT='"+contact+"'"+"WHERE ID ="+id;
				Statement stmt= con.createStatement();
				int count = stmt.executeUpdate(query);
				if(count>0) {
					System.out.println("   --- RESERVATION UPDATED ---   ");
				}else {
					System.out.println("   --- RESERVATION UPDATE FAILED ---   ");
				}			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private static void deleteReservation(Connection con,Scanner scn) {
			try {
				System.out.println("Enter Reservation Id to Delete : ");
				int id = scn.nextInt();
				if(!reservationExists(con,id)) {
					System.out.println("   --- Reservation Not Found ---   ");
					return;
				}
				String query = "DELETE FROM RESERVATION WHERE ID = "+id;
				Statement stmt=con.createStatement();
				int count = stmt.executeUpdate(query);
				if(count>0) {
					System.out.println("   --- RESERVATION DELETED ---   ");
				}else {
					System.out.println("   --- RESERVATION DELETE FAILED ---   ");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
	
		public static void exit() throws InterruptedException {
			System.out.println(" --- Exiting the System --- ");
			int i=5;
			while(i!=0) {
				System.out.print(",");
				Thread.sleep(3000);// will pause the execution of ,for 3 seconds every time util condition
				i--;       //becomes false
			}
			System.out.println();
			System.out.println(" --- Thank you for Using Hotel Reservation System --- ");
		}
		
		private static final String durl = "jdbc:mysql://localhost:3306/hotel";
		private static final String user = "root";
		private static final String password = "Best@123";
		
		
		
		
		public static void main(String[] args) {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				Connection con = DriverManager.getConnection(durl,user,password);
				while(true) {
					System.out.println();
					System.out.println("      Welcome      ");
					System.out.println(" Hotel Reservation System ");
					Scanner scn = new Scanner(System.in);
					System.out.println("1. RESERVE A ROOM ");
					System.out.println("2. VIEW RESERVATIONS ");
					System.out.println("3. GET A ROOM NUMBER");
					System.out.println("4. UPDATE RESERVATIONS ");
					System.out.println("5. DELETE RESERVATIONS ");
					System.out.println("0. EXIT ");
					System.out.println(" CHOOSE AN ANY OPTION ");
					int choice = scn.nextInt();
					switch(choice) {
					case 1 :
						reservationRoom(con, scn);
						break;
					case 2 :
						viewReservations(con);
						break;
						
					case 3 : 
						getRoomNumber(con, scn);
						break;
					case 4 : 
						updateReservations(con, scn);
						break;
					case 5 :
						deleteReservation(con, scn);
						break;
					case 0 :
						exit();
						scn.close();
						return;
					default :
						System.out.println("Invalid choice ... try again!!!");
					
					}
				}
					
					
					
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
