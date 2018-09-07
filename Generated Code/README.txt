  		-----------------------------------------------------------------------
  		Generated with SimGen v1.0.0
  		Created by Michal Pasternak at Queen's University MASE lab
  		-----------------------------------------------------------------------
  		This generated simulation "simulation" was created by: Michal
  		The purpose of this simulation is to:"MASE Challenge Problem"
  		
  		NOTICE!!!
  		Channel control1 requires port 8888 to be available for connection on the host machine.
  		Channel control2 requires port 8887 to be available for connection on the host machine.
  		Channel control3 requires port 8886 to be available for connection on the host machine.
  		-----------------------------------------------------------------------
  		
  		Connecting on Port 8888 will allow you to control: Rover
  		Connecting on Port 8887 will allow you to control: Target
  		Connecting on Port 8886 will allow you to control: Rover,Target
  		WARNING: The following instantiated objects can not be controlled: plane 

  		
  		-----------------------------------------------------------------------
  		You can interact with Target by using these actions:
  		
  		Action: setForwardPower
  		Message Syntax: Target,setForwardPower(int)
  		Returns:  No Return
  		Description: change the power of all wheels to this value
  		Action: incrementPower
  		Message Syntax: Target,incrementPower(int)
  		Returns:  No Return
  		Description: Increment the power of all wheels by the amount
  		Action: setLRPower
  		Message Syntax: Target,setLRPower(int,int)
  		Returns:  No Return
  		Description: Set the power of the left and right wheels
  		Action: brake
  		Message Syntax: Target,brake(int)
  		Returns:  No Return
  		Description: Apply the amount of brake power to all wheels
  		Action: setFR
  		Message Syntax: Target,setFR(int)
  		Returns:  No Return
  		Description: Set Front Right Motor power
  		Action: setMR
  		Message Syntax: Target,setMR(int)
  		Returns:  No Return
  		Description: Set Middle Right Motor power
  		Action: setBR
  		Message Syntax: Target,setBR(int)
  		Returns:  No Return
  		Description: Set Back Right Motor power
  		Action: setFL
  		Message Syntax: Target,setFL(int)
  		Returns:  No Return
  		Description: Set Front Left Motor power
  		Action: setML
  		Message Syntax: Target,setML(int)
  		Returns:  No Return
  		Description: Set Middle Left Motor power
  		Action: setBL
  		Message Syntax: Target,setBL(int)
  		Returns:  No Return
  		Description: Set Back Left Motor power
  		Action: GPSy
  		Message Syntax: Target,GPSy()
  		Returns: String in form "Target,real;"
  		Description: get the Y coordinate
  		Action: GPSx
  		Message Syntax: Target,GPSx()
  		Returns: String in form "Target,real;"
  		Description: get the X coordinate
  		Action: GPSz
  		Message Syntax: Target,GPSz()
  		Returns: String in form "Target,real;"
  		Description: get the Z coordinate
  		Action: getCompass
  		Message Syntax: Target,getCompass()
  		Returns: String in form "Target,real;"
  		Description:  get the degrees clockwise of North
  		 
  		-----------------------------------------------------------------------
  		Target inherits these actions from the metaModel:
  		
  		Action: setPos
  		Message Syntax: Target,setPos()
  		Returns:  No Return
  		Description: Set the position of the rover to 1,1
  		
  		
  		-----------------------------------------------------------------------
  		You can interact with Rover by using these actions:
  		
  		Action: setForwardPower
  		Message Syntax: Rover,setForwardPower(int)
  		Returns:  No Return
  		Description: change the power of all wheels to this value
  		Action: incrementPower
  		Message Syntax: Rover,incrementPower(int)
  		Returns:  No Return
  		Description: Increment the power of all wheels by the amount
  		Action: setLRPower
  		Message Syntax: Rover,setLRPower(int,int)
  		Returns:  No Return
  		Description: Set the power of the left and right wheels
  		Action: brake
  		Message Syntax: Rover,brake(int)
  		Returns:  No Return
  		Description: Apply the amount of brake power to all wheels
  		Action: setFR
  		Message Syntax: Rover,setFR(int)
  		Returns:  No Return
  		Description: Set Front Right Motor power
  		Action: setMR
  		Message Syntax: Rover,setMR(int)
  		Returns:  No Return
  		Description: Set Middle Right Motor power
  		Action: setBR
  		Message Syntax: Rover,setBR(int)
  		Returns:  No Return
  		Description: Set Back Right Motor power
  		Action: setFL
  		Message Syntax: Rover,setFL(int)
  		Returns:  No Return
  		Description: Set Front Left Motor power
  		Action: setML
  		Message Syntax: Rover,setML(int)
  		Returns:  No Return
  		Description: Set Middle Left Motor power
  		Action: setBL
  		Message Syntax: Rover,setBL(int)
  		Returns:  No Return
  		Description: Set Back Left Motor power
  		Action: GPS
  		Message Syntax: Rover,GPS()
  		Returns: String in form "Rover,real,real;"
  		Description: get the X and Z coordinate
  		Action: getCompass
  		Message Syntax: Rover,getCompass()
  		Returns: String in form "Rover,real;"
  		Description:  get the degrees clockwise of North
  		 
  		-----------------------------------------------------------------------
  		Rover inherits these actions from the metaModel:
  		
  		Action: setPos
  		Message Syntax: Rover,setPos()
  		Returns:  No Return
  		Description: Set the position of the rover to 1,1
  		
  		
