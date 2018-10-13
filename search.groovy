import java.util.logging.Logger
import java.util.regex.Matcher

class search {

	static main(args) {
		
		Logger logger = Logger.getLogger("")
		
		//Start time to Log
		def startTime = new Date()
		logger.info ("Start :" + startTime.toString())
		 
		
		//Variables
		
		//Arguments 
		
		
		if (args.length<2) {
			println "Enter arguments:  filepath pattern word"
			System.exit(0)
		}
		
		def path = args[0]
		def keyword = args[1]
		def change = args[2]
		def flag=0
		
		//
		def fileText
		def fileName
		def filePath

		File newFile
		File backup
		
		
		//Check if there is an argument for output file
		if (args.length>3) {
			def output  = args[3]
			
			//Create output.txt with filenames where patterns found
			flag=1										
			newFile = new File( output	 + "/output.txt")	
			newFile.createNewFile()
			
		}
		
		//Iterate of a path with folder and subfolders
		new File(path).eachFileRecurse  { file ->

			//Check if filepath is file
			if(file.file) {
				
				// Assign text, name and path of file to variables
				
				fileText = file.text
				fileName = file.name
				filePath = file.getParent()

				// Check if there is a match with given pattern or word from the argument in the file
				if(fileText =~ keyword)		{
						
						logger.info ("Pattern found at: :" + file.name)
					
						// Write name of the file to the list
						if(flag) {
							
							assert newFile<<file.getName() + "\n"
						
						}
						
						// Backup file before replacing word	
						backup  = new File( "$filePath" +"/" + "Backup_$fileName")	
						backup.write(fileText)
						
						//replace Word or patter with given Word
						fileText = fileText.replaceAll(keyword, change)
						file.write(fileText)
					
					}
			}
		}
		
		


	
	
	
	
		//Log End Time
		def endTime = new Date()
		logger.info ("endTime :" + endTime.toString())
		
}
}
