package com.tj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.ExpiresFilter.XPrintWriter;

public class UserDAO {

	public ArrayList<UserVO> getUserList(String filepath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath)); // Stream(데이터의 흐름관리)
			// FileReader는 문자열이 아닌 한 문자씩 읽어오며, BufferedReader는 문자들을 모아 한 행을 읽어옴 
			String line = null; // br을 저장할 변수 line선언 및 null초기화
			ArrayList<UserVO> list = new ArrayList<>(); // ArrayList에 담을 자료형<UserVO>을 지정
			while((line=br.readLine())!=null) { // 한 행씩 읽어오며 null이 나오기 전까지 반복							
				list.add(parseLine(line)); // 불러온 각 행을 객체화해서 list에 저장
			}
			return list;
		}catch(Exception ex) {
			return null;
		}		
	}
	private UserVO parseLine(String line) { // line은 정보의 한 행이 한 객체로 modeling
		String[] token = line.split(",");
		UserVO u = new UserVO(); // VO(Value Object)는 Entity와 동일한 의미를 가진다(기능x 속성o)
		u.setNum(Integer.parseInt(token[0]));
		u.setName(token[1]);
		u.setPhone(token[2]);
		u.setEmail(token[3]);
		return u;
	}
	public UserVO getUser(String path, int num) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			ArrayList<UserVO> list = getUserList(path);
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getNum()==num) return list.get(i);
			}
		}catch(Exception ex) {
			return null;
		}
		return null;
	}
	public boolean save(String path, UserVO u) {
		try {
			System.out.printf("%d,%s,%s,%s", u.getNum(), u.getName(), u.getPhone(), u.getEmail());
			
			PrintWriter toFile = new PrintWriter(new FileWriter(path, true));
			toFile.printf("%d,%s,%s,%s", u.getNum(), u.getName(), u.getPhone(), u.getEmail());
			toFile.println();
			toFile.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(String filepath, int no) {
		try { // 파일에 저장된 목록을 읽어서 메모리에 로드
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String line = null;
			ArrayList<String> list = new ArrayList<>();
			boolean found = false;			
			while((line=br.readLine())!=null) {
				if(line.split(",")[0].equals(""+no)) {
					found = true;
					continue;
				}
				list.add(line);
			}
	
			br.close();
			PrintWriter toFile = new PrintWriter(new FileWriter(filepath));
			for(int i=0;i<list.size();i++) {
				toFile.println(list.get(i));
			}
			toFile.close();
			if(found) return true;
			}catch(Exception ex) {
				return false;
			}
			return false;
		}
		
	public boolean update(String filepath, UserVO u) {
		
		try { // 파일에 저장된 목록을 읽어서 메모리에 로드
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String line = null;
			ArrayList<String> list = new ArrayList<>();
			boolean found = false;			
			while((line=br.readLine())!=null) {
				String[] fileInfo = line.split(",");
				if(fileInfo[0].equals(""+u.getNum())) {
					found = true;
					line = String.format("%d,%s,%s,%s",
							u.getNum(),fileInfo[1], u.getPhone(), u.getEmail());
				}
				list.add(line);
			}
	
			br.close();
			PrintWriter toFile = new PrintWriter(new FileWriter(filepath));
			for(int i=0;i<list.size();i++) {
				toFile.println(list.get(i));
			}
			toFile.close();
			if(found) return true;
			}catch(Exception ex) {
				return false;
			}
			return false;
	}	
}
