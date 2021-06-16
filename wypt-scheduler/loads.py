import pickle



if __name__ == '__main__':
    f = open('/Users/zcm/Documents/GitHub/newword/wypt-scheduler/p.text', 'r')
    bb = pickle.load(f)
    bb.show()
