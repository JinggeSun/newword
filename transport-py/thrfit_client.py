# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
from thrift.protocol import TCompactProtocol
from thrift.transport import TSocket, TTransport
from thrift import Thrift

from py.thrift.generated import PersonService


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press ⌘F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
def get_thrift_client():
    try:
        tSocket = TSocket.TSocket('localhost',8899)
        tSocket.setTimeout(600)
        transport = TTransport.TFramedTransport(tSocket)
        protocol = TCompactProtocol.TCompactProtocol(transport)
        client = PersonService.Client(protocol)
        transport.open()
        return client
    except Thrift.TException as te:
        print(te.message)















