/*
 * 작성자: 장보늬
 * 작성날짜: 2023-10-16
 * 내용: 알고리즘문제에서 코드실행 후 도출되는 결과 조회한다.
 */

package com.ktdsuniversity.edu.coderuntime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ktdsuniversity.edu.util.XssIgnoreUtil;

@Component
@Scope("prototype")
public class CodeRuntime {

	public static StringBuffer runFileCode;
	
	@Value("${app.multipart.base-dir:C:/uploadFiles}")
	private String baseDir;

	private StringBuffer code;
	
	private String fileName;
	private File javaFile;
	
	private File runFile;
	
	static {
		runFileCode = new StringBuffer();
		runFileCode.append("import com.google.gson.Gson;\n");
		runFileCode.append("import java.lang.reflect.Method;\n");
		runFileCode.append("import java.lang.reflect.Parameter;\n");
		runFileCode.append("import java.lang.reflect.InvocationTargetException;\n");
		runFileCode.append("import java.util.Arrays;\n");
		runFileCode.append("import java.util.List;\n");
		runFileCode.append("import java.util.Map;\n");
		runFileCode.append("import java.util.HashMap;\n");
		runFileCode.append("import java.util.stream.Collectors;\n");
		
		runFileCode.append("public class Run {\n");
		runFileCode.append("\n");
		runFileCode.append("    public static void main(String[] args) {\n");
		runFileCode.append("        String argument = args.length > 0 ? Arrays.stream(args).collect(Collectors.joining(\" \")) : null;\n");
		runFileCode.append("        Gson gson = new Gson();\n");
		runFileCode.append("        Map<String, Object> param = argument != null ? gson.fromJson(argument, Map.class) : new HashMap<>();\n");
		runFileCode.append("        Solution solution = new Solution();\n");
		runFileCode.append("        Method[] methodArray = solution.getClass().getDeclaredMethods();\n");
		runFileCode.append("        for (Method method: methodArray) {\n");
		runFileCode.append("        	String name = method.getName();\n");
		runFileCode.append("        	if (name.equals(\"solution\")) { \n");
		runFileCode.append("        		int paramCount = method.getParameterCount();\n");
		runFileCode.append("        		\n");
		runFileCode.append("        		Object[] parameters = null;\n");
		runFileCode.append("        		if (paramCount > 0) {\n");
		runFileCode.append("        			parameters = new Object[paramCount];\n");
		runFileCode.append("        			Parameter[] paramList = method.getParameters();\n");
		runFileCode.append("        			for(int i = 0; i < paramList.length; i++) { \n");
		runFileCode.append("        				Parameter each = paramList[i];\n");
		runFileCode.append("        				\n");
		runFileCode.append("        				String paramName = each.getName();\n");
		runFileCode.append("        				String typeName = each.getParameterizedType().getTypeName();\n");
		runFileCode.append("        				\n");
		runFileCode.append("        				parameters[i] = param.get(paramName);\n");
		runFileCode.append("        				if (typeName.equals(\"int\")) {\n");
		runFileCode.append("        					parameters[i] = (int) Double.parseDouble(parameters[i].toString());\n");
		runFileCode.append("        				}\n");
		runFileCode.append("        				else if (typeName.equals(\"double\")) {\n");
		runFileCode.append("        					parameters[i] = Double.parseDouble(parameters[i].toString());\n");
		runFileCode.append("        				}\n");
		runFileCode.append("        				else if (typeName.equals(\"float\")) {\n");
		runFileCode.append("        					parameters[i] = Float.parseFloat(parameters[i].toString());\n");
		runFileCode.append("        				}\n");
		runFileCode.append("        				else if (typeName.endsWith(\".String\")) {\n");
		runFileCode.append("        					parameters[i] = parameters[i].toString();\n");
		runFileCode.append("        				}\n");
		runFileCode.append("        				else if (typeName.endsWith(\".String[]\")) {\n");
		runFileCode.append("        					List tempList = (List)parameters[i];\n");
		runFileCode.append("        					String[] tempArray = new String[tempList.size()];\n");
		runFileCode.append("        					\n");
		runFileCode.append("        					for(int j = 0; j < tempList.size(); j++) {\n");
		runFileCode.append("        						tempArray[j] = tempList.get(j).toString();\n");
		runFileCode.append("        					}\n");
		runFileCode.append("        					\n");
		runFileCode.append("        					parameters[i] = tempArray;\n");
		runFileCode.append("        				}\n");
		runFileCode.append("        				else if (typeName.equals(\"int[]\")) {\n");
		runFileCode.append("        					List tempList = (List)parameters[i];\n");
		runFileCode.append("        					int[] tempArray = new int[tempList.size()];\n");
		runFileCode.append("        					\n");
		runFileCode.append("        					for(int j = 0; j < tempList.size(); j++) {\n");
		runFileCode.append("        						tempArray[j] = (int) Double.parseDouble(tempList.get(j).toString());\n");
		runFileCode.append("        					}\n");
		runFileCode.append("        					\n");
		runFileCode.append("        					parameters[i] = tempArray;\n");
		runFileCode.append("        				}\n");
		runFileCode.append("        				else if (typeName.equals(\"double[]\")) {\n");
		runFileCode.append("        					List tempList = (List)parameters[i];\n");
		runFileCode.append("        					double[] tempArray = new double[tempList.size()];\n");
		runFileCode.append("        					\n");
		runFileCode.append("        					for(int j = 0; j < tempList.size(); j++) {\n");
		runFileCode.append("        						tempArray[j] = Double.parseDouble(tempList.get(j).toString());\n");
		runFileCode.append("        					}\n");
		runFileCode.append("        					\n");
		runFileCode.append("        					parameters[i] = tempArray;\n");
		runFileCode.append("        				}\n");
		runFileCode.append("        				else if (typeName.equals(\"float[]\")) {\n");
		runFileCode.append("        					List tempList = (List)parameters[i];\n");
		runFileCode.append("        					float[] tempArray = new float[tempList.size()];\n");
		runFileCode.append("        					\n");
		runFileCode.append("        					for(int j = 0; j < tempList.size(); j++) {\n");
		runFileCode.append("        						tempArray[j] = Float.parseFloat(tempList.get(j).toString());\n");
		runFileCode.append("        					}\n");
		runFileCode.append("        					\n");
		runFileCode.append("        					parameters[i] = tempArray;\n");
		runFileCode.append("        				}\n");
		runFileCode.append("        			}\n");
		runFileCode.append("        		}\n");
		runFileCode.append("        		\n");
		runFileCode.append("        		try {\n");
		runFileCode.append("        			long now = System.currentTimeMillis();\n");
		runFileCode.append("        			Object result = method.invoke(solution, parameters);\n");
		runFileCode.append("        			float processTime = (System.currentTimeMillis() - now) / 1000.0f;\n");
		runFileCode.append("        			System.out.println(\"걸린시간: \" + processTime + \"ms\");\n");
		runFileCode.append("        			if (result != null) {\n");
		runFileCode.append("        				System.out.println(result);\n");
		runFileCode.append("        			}\n");
		runFileCode.append("        		} catch (IllegalAccessException | InvocationTargetException e) {\n");
		runFileCode.append("        			e.printStackTrace();\n");
		runFileCode.append("        		}\n");
		runFileCode.append("        		break;\n");
		runFileCode.append("        	}\n");
		runFileCode.append("        }\n");
		runFileCode.append("    }\n");
		runFileCode.append("\n");
		runFileCode.append("}");
	}
	
	public void setCode(String code) {
		this.code = new StringBuffer();
		
		String[] codeLine = code.split("\n");
		for (String line: codeLine) {
			this.code.append(line.replace("\r", "") + "\n");
		}
		
		this.fileName = code.substring(code.indexOf("class") + 5, code.indexOf("{"));
		this.fileName = this.fileName.trim() + ".java";
		this.setFileName(fileName);
		
		this.makeFile();
		this.makeRunFile();
		this.doCompileJava();
		this.doCompileRun();
	}
	
	public void setFileName(String fileName) {
		String codeDir = this.baseDir + "\\" + UUID.randomUUID().toString();
		this.fileName = fileName;

		this.javaFile = new File(codeDir, this.fileName);
		if (!javaFile.getParentFile().exists()) {
			javaFile.getParentFile().mkdirs();
		}
		
		this.runFile = new File(codeDir, "Run.java");
		if (!runFile.getParentFile().exists()) {
			runFile.getParentFile().mkdirs();
		}
	}

	private void makeFile() {
		String code = this.code.toString();
		code = XssIgnoreUtil.ignoreText(code);
		code = Jsoup.parse(code).text();
		
		code = code.replace("\t", " ");
		while ( code.contains("  ") ) {
			code = code.replace("  ", " ");
		}
		
		if ( !code.contains("public class Solution") && code.contains("class Solution") ) {
			code = code.replace("class Solution", "public class Solution");
		}
		
		makeFile(code);
//		makeFile(CodeRuntime.runFileCode.toString());
	}
	
	private void makeRunFile() {
		String code = CodeRuntime.runFileCode.toString();
//		if (testCode != null) {
//			code = code.replace("#codeHear#", testCode);
//		}
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(runFile));
			bw.write(code);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	public void makeFile(String code) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(javaFile));
			bw.write(code);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	private void doCompileJava() {
		doCompile(this.javaFile);
	}
	
	private void doCompileRun() {
		doCompile(this.runFile);
	}
	
	private synchronized void doCompile(File file) {
		
		System.out.println("compile: " + "javac -parameters --class-path " + file.getParentFile().getParentFile().getAbsolutePath() + "/gson-2.10.1.jar;" + file.getParentFile().getAbsolutePath() + " " + file.getAbsolutePath());
		
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "javac -parameters --class-path " + file.getParentFile().getParentFile().getAbsolutePath() + "/gson-2.10.1.jar;" + file.getParentFile().getAbsolutePath() + " " + file.getAbsolutePath());
		Process process = null;
		try {
			process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuffer buffer = new StringBuffer();
		String line = null;
		try {
			
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}
		}
	}
	
	public List<String> run(String paramJson) {
		String classFile = this.runFile.getAbsolutePath();
		String command = "java -Dfile.encoding=UTF8 --class-path " + this.runFile.getParentFile().getParentFile().getAbsolutePath() + "/gson-2.10.1.jar;" + this.runFile.getParentFile().getAbsolutePath() + " " + classFile;
		
		if (paramJson != null && paramJson.trim().length() == 0) {
			paramJson = null;
		}
		if (paramJson != null) {
			paramJson = paramJson.trim().replace("\"", "\"\"");
			command += " \"" + paramJson + "\"";
		}
		
		System.out.println("Run: " + command);
		
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", command);
		Process process = null;
		try {
			process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		List<String> runResult = new ArrayList<>();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				if (line.trim().length() > 0) {
					runResult.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}
		}
		return runResult;
	}
	
	public void teardown() {
		this.code = null;
		this.fileName = null;
		this.javaFile = null;
		this.runFile = null;
	}
	
}
