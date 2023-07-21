import asyncio
import websocket
import threading


def on_bind(websocket):
	websocket.send("Connect success!")

def on_message(websocket, message):
	def run(*args):
		print(message)
		websocket.close()
		print("Message recv...")

	threading.Thread(target=run).start()

def on_close(websocket, close_status_code, close_message):
	print("Closed!")

if __name__ == "__main__":
	# 192.168.31.166:8888 에 서버 할당
	# server = websockets.serve(accept, "192.168.31.166", 8888)
	websocket.enableTrace(True)
	websocket_app = websocket.WebSocketApp("ws://192.168.31.166",8888, on_open=on_bind, on_message=on_message, on_close=on_close)
	websocket_app.run_forever()
	# 비동기 선언
	# asyncio.get_event_loop().run_until_complete(server)
	# asyncio.get_event_loop().run_forever()