package org.pretend.common;

public class MethodDescription {
	
	private String description;
	
	private String belongTo;
	
	private String simpleReturnType;
	
	private String[] parameterTypes;
	
	private String[] simpleParameterTypes;
	
	private int parameterCount;
	
	private String name;
	
	private String returnType;

	public String getDescription() {
		
		return description;
	}

	public void setDescription(String description) {
		StringBuilder sb = new StringBuilder();
		sb.append(simpleReturnType).append(" ").append(name).append("(");
		if(null != simpleParameterTypes && simpleParameterTypes.length > 0){
			for (int i = 0; i < simpleParameterTypes.length; i++) {
				sb.append(simpleParameterTypes[i]);
				if(i<simpleParameterTypes.length-1){
					sb.append(",");
				}
            }
		}
		sb.append(")");
		this.description = sb.toString();
	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}

	public String getSimpleReturnType() {
		return simpleReturnType;
	}

	public void setSimpleReturnType(String simpleReturnType) {
		this.simpleReturnType = simpleReturnType;
	}

	public String[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(String[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public int getParameterCount() {
		return parameterCount;
	}

	public void setParameterCount(int parameterCount) {
		this.parameterCount = parameterCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getSimpleParameterTypes() {
		return simpleParameterTypes;
	}

	public void setSimpleParameterTypes(String[] simpleParameterTypes) {
		this.simpleParameterTypes = simpleParameterTypes;
	}
	
	

}
