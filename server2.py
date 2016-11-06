import socket      
import random         
s = socket.socket()         
host = '192.168.0.15' 
port = 1297                
s.bind((host, port))        
s.listen(10) 
while True:
   temperature = str(random.randrange(-15,50))                
   c, addr = s.accept()     
   print 'Got connection from  ', addr[0]
   c.send(temperature)
   c.close()               
