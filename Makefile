# Author        : Shivani Sanjay Tergaonkar
# File          : Makefile
# Creation Date : 02/28/2021
# Description   : This file contains command line 
#                 options to build Bankers Simulation project

OBJ_PATH  = bin
OBJ       = Bankers
CC        = javac
CC_FLAGS  = -d
SRC       = src/Bankers.java
RUNCC     = java
RUN_FLAGS = -classpath

all:	
	@ echo --------------------------------------------------
	@ echo   Building Bankers Simulation program for COEN-283 class 
	mkdir -p $(OBJ_PATH)
	$(CC) $(CC_FLAGS) $(OBJ_PATH) $(SRC)
	@ echo   Build successful                         
	@ echo   © Property of Shivani Sanjay Tergaonkar
	@ echo --------------------------------------------------

clean:
	@ echo ---------------------------------------------
	@ echo   Deleting object files                      
	rm -rf $(OBJ_PATH)
	@ echo   Object files deleted                      
	@ echo ---------------------------------------------

run: 
	@ echo ---------------------------------------------
	@ echo   Running Bankers Simulation program 
	$(RUNCC) $(RUN_FLAGS) $(OBJ_PATH) $(OBJ)
	@ echo   Bankers Simulation Program completed!
	@ echo ---------------------------------------------


