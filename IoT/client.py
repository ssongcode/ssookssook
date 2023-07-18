from websocket import create_connection

if __name__ == "__main__":
	ws = create_connection("ws://192.168.86.231:8001")
	print("Connect")
	ws.close()
	print("Disconnect")
