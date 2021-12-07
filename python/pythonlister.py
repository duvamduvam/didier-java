from py4j.java_gateway import JavaGateway, CallbackServerParameters


class PythonListener(object):

    def __init__(self, gateway):
        self.gateway = gateway

    def notify(self, obj):
        print("Notified by Java")
        print(obj)
        gateway.jvm.System.out.println("Hello from python!")
        strip = gateway.entry_point.getStrip()

        stripSize = strip.getSize()
        i = 0
        for i in range(stripSize):

            led = strip.pop()

            red = led.getRed()
            green = led.getGreen()
            blue = led.getBlue()

            print("red ")
            print(red)
            print("green ")
            print(green)
            print("blue ")
            print(blue)



        return "A Return Value"

    class Java:
        implements = ["fr.didier.python.PythonListener"]

if __name__ == "__main__":
    gateway = JavaGateway(
        callback_server_parameters=CallbackServerParameters())

    listener = PythonListener(gateway)
    gateway.entry_point.registerListener(listener)
    gateway.entry_point.notifyAllListeners()
    #gateway.shutdown()

