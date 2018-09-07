using System.Collections;
using System.Collections.Generic;
using UnityEngine;
   		
public class starter : MonoBehaviour {
// ---- These must be assigned as prefabs in Unity and as "metaobjects" in the DSL
	public sateliteCameraScript Camera;

	
	GameObject CameraObject;
GameObject planeObject;
GameObject TargetObject;
GameObject RoverObject;
//-----
	void Start () {
	Application.runInBackground = true;
//-- adding the necessary instances
	CameraObject = (GameObject)Instantiate (Resources.Load("CameraController"),transform);
   	
   	planeObject = (GameObject)Instantiate (Resources.Load("Plane"),transform);
   	planeObject.AddComponent<planeScript>();
   	TargetObject = (GameObject)Instantiate (Resources.Load("Rover"),transform);
   	TargetObject.AddComponent<TargetScript>();
   	RoverObject = (GameObject)Instantiate (Resources.Load("Rover"),transform);
   	RoverObject.AddComponent<RoverScript>();
//adding the necessary channel information scripts	
	Camera = CameraObject.GetComponent<sateliteCameraScript>();
   	Camera.observedObject = RoverObject;
   	Camera.off = new Vector3(0.0f,0.0f,0.0f);
   	gameObject.AddComponent<ChannelControllercontrol1>();
   	gameObject.AddComponent<ChannelControllercontrol2>();
   	gameObject.AddComponent<ChannelControllercontrol3>();
	}
	

		void Update(){//
		
			if (Input.GetKeyDown(KeyCode.Alpha1)){
				Camera.observedObject = planeObject;
			}
		
			if (Input.GetKeyDown(KeyCode.Alpha2)){
				Camera.observedObject = TargetObject;
			}
		
			if (Input.GetKeyDown(KeyCode.Alpha3)){
				Camera.observedObject = RoverObject;
			}
			if (Input.GetKeyDown(KeyCode.Z)){
				Camera.follow = true;
			}
			if (Input.GetKeyDown(KeyCode.X)){ 
				Camera.follow = false;
			}
			if (Input.GetKeyDown(KeyCode.LeftArrow)){
						Camera.transform.Rotate (0, -10, 0);
					}
					if (Input.GetKeyDown(KeyCode.RightArrow)){
						Camera.transform.Rotate (0, 10, 0);
					}
		}
}
