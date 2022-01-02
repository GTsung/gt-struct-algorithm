~~~~~

    并发设计: 
        不变模式, 即让共享变量只有读操作而无写操作，这时不会出现并发问题

        将类的所有属性都设置为final 且只提供读操作方法，严格来说将类也修饰为final

        如果这个不可变类需要修改，那么只能重新创建一个该不可变类而不是修改状态
        
        每次修改都要创建会消耗内存，这时可以使用享元模式，即池化缓存，例如
            Integer.valueOf() ---> 这个-128~~127都是从池子中取得

        因此这种基本类型的包装类都不适合做锁，因为锁定这些包装类型不能保证是不是不同的锁

        class A {
            Long al = Long.valueOf(1);
            public void setAl() {
                synchronized(al) {
                    // do sth
                }
            }
        }

        class B {
            Long bl = Long.valueOf(1);
            public void setBl() {
                synchronized(bl) {
                    // do sth
                }
            }
        }
    
    A 与 B 都使用的相同的锁，本来这两个类的锁不应该是竞争的，但这里Long.valueOf(1)
是同一个对象，因此实际上引发了竞争


        另外当属性为对象时，即使属性被final修饰，但这个属性对象的内部属性也会变化，
    这时需要研究属性对象的属性是否也需要final修饰
        另外需要正确发布
~~~~~