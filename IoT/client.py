import asyncio
import websockets
import json
import serial
import requests
import cv2
import os
import socket
from datetime import datetime

PORT = 'COM5' # 라즈베리 파이 PORT의 경우 확인 필요
BaudRate = 9600 # 통신 속도 - 라즈베리파이4는 9600이 적정
ARD = serial.Serial(PORT, BaudRate) # 아두이노 통신 설정 - PC
# ARD = serial.Serial("/dev/ttyACM0",BaudRate) # 아두이노 통신 설정 - 라즈베리파이4
# async def connect_and_subscribe():
#     uri = "ws://localhost:8080/stomp/chat"  # Spring Boot WebSocket Endpoint URL
#     username = "your_username"  # Replace with your username
#     password = "your_password"  # Replace with your password
#     destination = "/sub/chat/room/c5e9f525-0cbd-4191-9cfa-248b1cfb0131"  # The topic to subscribe to

#     async with websockets.connect(uri) as websocket:
#         # Send STOMP CONNECT frame with credentials
#         connect_frame = f"CONNECT\naccept-version:1.2\nlogin:{username}\npasscode:{password}\n\n\x00"
#         await websocket.send(connect_frame.encode())

#         # Send STOMP SUBSCRIBE frame to subscribe to the destination
#         subscribe_frame = f"SUBSCRIBE\ndestination:{destination}\nid:sub-1\nack:auto\n\n\x00"
#         await websocket.send(subscribe_frame.encode())

#         while True:
#             response = await websocket.recv()
#             print("Received message:", response)

# async def send_json_message():
#     uri = "ws://localhost:8080/stomp/chat"  # Spring Boot WebSocket Endpoint URL
#     username = "your_username"  # Replace with your username
#     password = "your_password"  # Replace with your password
#     destination = "/pub/chat/message"  # The destination to send the JSON message

#     async with websockets.connect(uri) as websocket:
#         # Send STOMP CONNECT frame with credentials
#         connect_frame = f"CONNECT\naccept-version:1.2\nlogin:{username}\npasscode:{password}\n\n\x00"
#         await websocket.send(connect_frame.encode())

#         # Create a JSON message
#         json_message = {
#             "roomId" : "c5e9f525-0cbd-4191-9cfa-248b1cfb0131",
#             "message" : "hi",
#             "writer" : "testVVVDDDddddd"
#             }

#         # Convert the JSON message to a string and send it as the STOMP SEND frame
#         send_frame = f"SEND\ndestination:{destination}\ncontent-type:application/json\n\n{json.dumps(json_message)}\x00"
#         await websocket.send(send_frame.encode())
def read():
	if ARD.readable():
		line = ARD.readline()
		temperature, humidity, groundMoisture, waterTank = map(int,line.decode().split())
		print("temperature :",temperature)
		print("humidity :", humidity)
		print("groundMoisture :",groundMoisture)
		print("waterTank :",waterTank)
	else:
		print("Read Failed!!")

def send_image_to_server():
	# 카메라 세팅
	cam = cv2.VideoCapture(0)
	# 카메라가 열렸는지 체크
	if not cam.isOpened():
		print("카메라를 열 수 없습니다.")
		return
	# 파일명 : 시리얼 넘버 + 현재시간.jpg

	serial_number = ""
	with open("../serial_number.txt","r") as file:
		serial_number = file.readline()
		if serial_number == "":
			print("시리얼 넘버가 없습니다.")
			return
	filename = "PLANT_"+serial_number+".jpg"
	status, frame = cam.read()
	capture_time = datetime.now().strftime("%Y-%M-%d %H:%M:%S")
	if not status:
		print("프레임을 읽어올 수 없습니다.")
		return
	# 이미지 저장
	cv2.imwrite("../python37/img/"+filename,frame)
	# 카메라 종료
	cam.release()
	print("Capture request Complete!")
	# TM 체크
	plant_level = ""
	with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
		s.connect(('127.0.0.1', 8888))
		s.sendall("1".encode())
		resp = s.recv(1024)
		print(resp.decode())
		plant_level = resp.decode()
	# 서버로 전송
	# url = "http://localhost:8080/upload" # 이미지 전송 할 uri
	# dto = {
	# 	'image' : open(../python37/img/filenname, 'rb'),
	#	'pot_id' : 1,
	#   'capture_time' : capture_time
	# }
	# response = request.post(url, files=dto)
	# if response.status_code == 200:
	# 	print("이미지 업로드 성공")
	# else:
	# 	print("이미지 업로드 실패")

if __name__ == "__main__":
	# asyncio.get_event_loop().run_until_complete(send_json_message())
	# asyncio.get_event_loop().run_until_complete(connect_and_subscribe())
	while True:
		read()
		send_image_to_server()
