		using System.Collections;
   		using System.Collections.Generic;
   		using System;
   		using UnityEngine;
   		  		   		
   		public class TargetScript : RoverMetaObject {	
   		  	public string channelID;
   		
   		// --- auto declare all "properties"
   		// --- End auto declare properties
   		
   		// --- Initialize object size/position
   		void Start(){
   		   	requiredStart();
   		   	t.localScale = new Vector3 (1.0f,1.0f,1.0f);
   		   	t.localPosition = new Vector3(1.0f,1.8f,15.0f);
   		}
   		// --- End start function
   		
   		// --- Add all new actions
// -- DSL generated "actions"
   			   			
public void setForwardPower(int amount){
	rm.ColliderL1.motorTorque = amount;
	rm.ColliderL2.motorTorque = amount;
	rm.ColliderL3.motorTorque = amount;
	rm.ColliderR1.motorTorque = amount;
	rm.ColliderR2.motorTorque = amount;
	rm.ColliderR3.motorTorque = amount;
}
public void incrementPower(int amount){
	rm.ColliderL1.motorTorque = amount + rm.ColliderL1.motorTorque;
	rm.ColliderL2.motorTorque = amount + rm.ColliderL2.motorTorque;
	rm.ColliderL3.motorTorque = amount + rm.ColliderL3.motorTorque;
	rm.ColliderR1.motorTorque = amount + rm.ColliderR1.motorTorque;
	rm.ColliderR2.motorTorque = amount + rm.ColliderR2.motorTorque;
	rm.ColliderR3.motorTorque = amount + rm.ColliderR3.motorTorque;
}
public void setLRPower(int leftPower,int rightPower){
	rm.ColliderL1.motorTorque = leftPower;
	rm.ColliderL2.motorTorque = leftPower;
	rm.ColliderL3.motorTorque = leftPower;
	rm.ColliderR1.motorTorque = rightPower;
	rm.ColliderR2.motorTorque = rightPower;
	rm.ColliderR3.motorTorque = rightPower;
}
public void brake(int amount){
	rm.ColliderL1.brakeTorque = amount;
	rm.ColliderR1.brakeTorque = amount;
	rm.ColliderL2.brakeTorque = amount;
	rm.ColliderR2.brakeTorque = amount;
	rm.ColliderL3.brakeTorque = amount;
	rm.ColliderR3.brakeTorque = amount;
}
public void setFR(int amount){
	rm.ColliderR1.motorTorque = amount;
}
public void setMR(int amount){
	rm.ColliderR2.motorTorque = amount;
}
public void setBR(int amount){
	rm.ColliderR3.motorTorque = amount;
}
public void setFL(int amount){
	rm.ColliderL1.motorTorque = amount;
}
public void setML(int amount){
	rm.ColliderL2.motorTorque = amount;
}
public void setBL(int amount){
	rm.ColliderL3.motorTorque = amount;
}
public string GPSy(){
	double ret;
	ret = posy;
   	return ret+"";
}
public string GPSx(){
	double ret;
	ret = posx;
   	return ret+"";
}
public string GPSz(){
	double ret;
	ret = posz;
   	return ret+"";
}
public string getCompass(){
	double ret;
	ret = rm.compass;
   	return ret+"";
}
   		   		   	// --- End adding all new actions
   		   		   	// --- Overide existing actions
   		   		   	 // --- End overrideActions
   		   		   	 
   		   		   	 // --- Begin message handling
   		public string translate(string message){	   				
   		   				//--Get parameters as strings.
   		   				string name = message.Substring (0, message.IndexOf ('('));
   		   				message = cut(message,'(');
   		   				string [] param = extractParam(message);
   		   				
   		   		   		if(name == "setForwardPower"){
   		   		   		 setForwardPower(Int32.Parse(param[0]));
   		   		   		 }
   		   		   		if(name == "incrementPower"){
   		   		   		 incrementPower(Int32.Parse(param[0]));
   		   		   		 }
   		   		   		if(name == "setLRPower"){
   		   		   		 setLRPower(Int32.Parse(param[0]),Int32.Parse(param[1]));
   		   		   		 }
   		   		   		if(name == "brake"){
   		   		   		 brake(Int32.Parse(param[0]));
   		   		   		 }
   		   		   		if(name == "setFR"){
   		   		   		 setFR(Int32.Parse(param[0]));
   		   		   		 }
   		   		   		if(name == "setMR"){
   		   		   		 setMR(Int32.Parse(param[0]));
   		   		   		 }
   		   		   		if(name == "setBR"){
   		   		   		 setBR(Int32.Parse(param[0]));
   		   		   		 }
   		   		   		if(name == "setFL"){
   		   		   		 setFL(Int32.Parse(param[0]));
   		   		   		 }
   		   		   		if(name == "setML"){
   		   		   		 setML(Int32.Parse(param[0]));
   		   		   		 }
   		   		   		if(name == "setBL"){
   		   		   		 setBL(Int32.Parse(param[0]));
   		   		   		 }
   		   		   		if(name == "GPSy"){
   		   		   		return ""+ GPSy();
   		   		   		 }
   		   		   		if(name == "GPSx"){
   		   		   		return ""+ GPSx();
   		   		   		 }
   		   		   		if(name == "GPSz"){
   		   		   		return ""+ GPSz();
   		   		   		 }
   		   		   		if(name == "getCompass"){
   		   		   		return ""+ getCompass();
   		   		   		 }
   		   		   		if(name == "setPos"){
   		   		   		 setPos();
   		   		   		}
   		   		   	else return "";
   		   		   	return "";
   		   		   	} 
   		   		   	// --- End message handling
   		   		}
