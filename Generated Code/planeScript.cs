		using System.Collections;
   		using System.Collections.Generic;
   		using System;
   		using UnityEngine;
   		  		   		
   		public class planeScript : PlaneMetaObject {	
   		  	public string channelID;
   		
   		// --- auto declare all "properties"
   		// --- End auto declare properties
   		
   		// --- Initialize object size/position
   		void Start(){
   		   	requiredStart();
   		   	t.localScale = new Vector3 (60.0f,1.0f,60.0f);
   		   	t.localPosition = new Vector3(0.0f,0.0f,0.0f);
   		}
   		// --- End start function
   		
   		// --- Add all new actions
// -- DSL generated "actions"
   			   			
   		   		   	// --- End adding all new actions
   		   		   	// --- Overide existing actions
   		   		   	 // --- End overrideActions
   		   		   	 
   		   		   	 // --- Begin message handling
   		public string translate(string message){	   				
   		   				//--Get parameters as strings.
   		   				string name = message.Substring (0, message.IndexOf ('('));
   		   				message = cut(message,'(');
   		   				string [] param = extractParam(message);
   		   				
   		   		   	return "";
   		   		   	} 
   		   		   	// --- End message handling
   		   		}
