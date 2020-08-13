# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
from thrift.protocol import TCompactProtocol
from thrift.transport import TSocket, TTransport
from thrift import Thrift

from py.thrift.generated import PersonService
from thrfit_client import get_thrift_client


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press ⌘F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')
    client = get_thrift_client()
    client.getPersonByUserName('张三')













