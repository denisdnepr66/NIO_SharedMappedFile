public enum Operation {
    Writer(1),
    Reader(0);
    private long val = 1;
    Operation(long val){
        this.val = val;
    }

    public long getVal(){
        return val;
    }

}

