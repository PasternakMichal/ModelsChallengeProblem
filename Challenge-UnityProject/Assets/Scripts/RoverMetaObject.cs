using UnityEngine;
using System.Collections;

public class RoverMetaObject : MonoBehaviour {
	
	public Transform t;
	// --- Get neccessary access components
	public roverMover rm ;
	// --- End Get neccessary access components
	
	public double posx;
	public double posy;
	public double posz;
	
	public double sizex;
	public double sizey;
	public double sizez;
	
	void update(){
		posx = t.localPosition.x;
		posy = t.localPosition.y;
		posz = t.localPosition.z;
		
		sizex = t.localScale.x;
		sizey = t.localScale.y;
		sizez = t.localScale.z;
	}
	
	public void requiredStart(){
		t = GetComponent<Transform>();
		// --- Load components
		rm = GetComponent<roverMover> ();
		// --- End Load components
	}
	
	   		// -- DSL generated "actions"
	   			
public void setPos(){
	posx = 1;
	posy = 1;
	posz = posy;
}
	   			 // if start is not defined in metaobject we define it here.
	   			void Start(){
	   			 	requiredStart();
	   			}
	   		// -- End DSL generated functions
	   		
	// --- Utility functions

	public string cut(string message,char cutter){
		return message.Substring(message.IndexOf(cutter)+1);
	}
	
	public string[] extractParam(string message){
		message = message.Substring(message.IndexOf("(")+1,(message.IndexOf(")")));
		return message.Split(',');
	}
	
}
