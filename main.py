import random 

def MyBernoulli(p):
    U = random.random();
    X = 0
    if U < p:
        X = 1
    else:
        X = 0

    return X

n = 20 # number of computers in the network
r = 5 # max number of computers cleaned daily double
p = 0.1 # probability of spreading the virus

trials = 1
totalDays = 0
comps = [n]
tempInfectedCount = 1

while trials < 10001:

    infectedCount = 1;
    days = 0
    comps.append(1)
    # Initially computer  # 0 is infected (set to 1),
    # computers # 1 thru #19 are clean (set to 0).
    for i in range(1, n):
        comps.append(0)
    infectedComps = []
    while infectedCount != 0:
        days = days+1

    # For each wire "infected -> noninfected":
    # perform a Bernoulli trial with probability p=0.1
    # if success, computer #j becomes newly infected (set to 2).
        for i in range(n):
            for j in range(n):
                if comps[i] == 1 and comps[j] == 0:
                    x = MyBernoulli(p)
                    if (x == 1):
                        comps[j] = 2
                        infectedCount = infectedCount+1
                        infectedComps.append(i)

    # After that, mark all newly infected as simply infected.
        for i in range(n):
            if comps[i] == 2:
                comps[i] = 1
                tempInfectedCount = tempInfectedCount+1
    #clean
        if infectedCount <= r:
            infectedCount = 0
            for i in range(n):
                comps[i] = 0
        else:
            for i in range(r):
                if len(infectedComps) > 0:
                    num = random.random() 
                    len(infectedComps)
                    index = infectedComps.get(num)
                    comps[index] = 0
                    infectedComps.remove(num)
                    infectedCount = infectedCount-1


    totalDays = totalDays + days
    trials = trials+1

days = totalDays / (trials - 1)
compsNum = tempInfectedCount / (trials - 1)

