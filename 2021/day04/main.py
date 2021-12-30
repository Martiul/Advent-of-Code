class Board:
    SIZE = 5

    def __init__(self, values):
        if len(values) != 5 or len(values[0]) != self.SIZE:
            print("Board not 5x5 grid")
        self.marked = set()
        self.board = [[int(v) for v in row] for row in values]
    
    def mark_number(self, number):
        self.marked.add(number)

    def double_loop(self, lamb):
        for row in range(0, self.SIZE):
            for col in range(0, self.SIZE):
                lamb(row, col)

    def is_bingo(self):
        for row in range(0, self.SIZE):
            for col in range(0, self.SIZE):
                if self.board[row][col] not in self.marked:
                    break
                if col + 1 == self.SIZE:
                    return True
        
        for col in range(0, self.SIZE):
            for row in range(0, self.SIZE):
                if self.board[row][col] not in self.marked:
                    break
                if row + 1 == self.SIZE:
                    return True
        return False
            
    def sum_not_marked(self):
        sum = 0
        for row in range(0, self.SIZE):
            for col in range(0, self.SIZE):
                num = self.board[row][col]
                if num not in self.marked:
                    sum += num
        return sum


    def __repr__(self):
        return str(self.board)

    def __str__(self):
        return str(self.board)

def getBingo(fileName):
    f = open(fileName, "r")
    bingo_numbers = f.readline().split(",")
    bingo_numbers = [int(x) for x in bingo_numbers]
    
    boards = []
    new_board = []
    for line in f:
        line = line.strip()
        if len(line) == 0:
            new_board = []
        else:
            new_board.append(line.split())
            if len(new_board) == 5:
                boards.append(Board(new_board))
    return boards, bingo_numbers

def partOne(fileName):
    boards, bingo_numbers = getBingo(fileName)

    for bn in bingo_numbers:
        bn = int(bn)
        for b in boards:
            b.mark_number(bn)
            if b.is_bingo():
                return bn * b.sum_not_marked()
    return 0
    

def partTwo(fileName):
    boards, bingo_numbers = getBingo(fileName)

    bingoed_boards = set()
    for bn in bingo_numbers:
        bn = int(bn)
        for i in range(0, len(boards)):
            boards[i].mark_number(bn)
            if boards[i].is_bingo():
                bingoed_boards.add(i)
            if len(bingoed_boards) == len(boards):
                return bn * boards[i].sum_not_marked()
    return 0
    
if __name__ == "__main__":
    print(partOne("sample.txt"))
    print(partOne("input.txt"))
    print(partTwo("sample.txt"))
    print(partTwo("input.txt"))
