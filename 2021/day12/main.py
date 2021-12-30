paths = 0

def getInput(file_name):
    adj_list = {}
    visited = {}

    f = open(file_name, "r")
    for line in f:
        start, dest = line.strip().split("-")
        if start not in adj_list:
            adj_list[start] = [dest]
        else:
            adj_list[start].append(dest)
        
        if dest not in adj_list:
            adj_list[dest] = [start]
        else:
            adj_list[dest].append(start)
    for edge in adj_list:
        visited[edge] = False
    return adj_list, visited

def partOne(file_name):
    global paths
    paths = 0
    adj_list, visited = getInput(file_name)
    dfs(adj_list, visited, "start")
    return paths

def dfs(adj_list, visited, cur_edge):
    global paths
    if cur_edge == "end":
        paths += 1
        return
    visited[cur_edge] = True
    for next in adj_list[cur_edge]:
        if not visited[next] or next.isupper():
            dfs(adj_list, visited, next)
    visited[cur_edge] = False

    return

def partTwo(file_name):
    global paths
    paths = 0
    adj_list, visited = getInput(file_name)
    dfs2(adj_list, visited, "start", None)
    return paths

def dfs2(adj_list, visited, cur_edge, repeated_edge):
    global paths
    if cur_edge == "end":
        paths += 1
        return
    visited[cur_edge] = True
    for next in adj_list[cur_edge]:
        if not visited[next] or next.isupper():
            # print("Take ", next)
            dfs2(adj_list, visited, next, repeated_edge)
        elif visited[next] and repeated_edge is None and next.islower() and next != "start":
            # print("Take 2", next)
            dfs2(adj_list, visited, next, next)

    if cur_edge != repeated_edge:
        # backtracking on the repeated small cave should not unmark visited, leads to cycle
        visited[cur_edge] = False

    return

if __name__ == "__main__":
    print(partOne("sample.txt"))
    print(partOne("input.txt"))
    print(partTwo("sample.txt"))
    print(partTwo("input.txt"))
    

