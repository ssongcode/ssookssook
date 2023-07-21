import asyncio
import websockets
import socket
# import serial



# if __name__ == "__main__":
# 	client = create_connection("ws://192.168.31.166:8888")
# 	# s = serial.Serial("/dev/ttyACM0", 9600, timeout=1)
# 	# s.flush()
# 	while True:
# 		# if s.in_waiting > 0:
# 			# line = s.readline().decode('utf-8').rstrip()
# 		line = input()

# 		# else:
# 			# print("Connect Error!")
#	# ws.close()
async def connect():
	# Connect
	async with websockets.connect("ws://192.168.31.166:8888") as ws:
		input_data = ""
		while input_data != 'exit':
			# send to serverte
			input_data = input("send : ")
			await ws.send("Send from " + socket.gethostbyname(socket.gethostname()) + " " + input_data)
			# recv from server
			recv_data = await ws.recv()
			print("recv : ", recv_data)


if __name__ == "__main__":
	asyncio.get_event_loop().run_until_complete(connect())