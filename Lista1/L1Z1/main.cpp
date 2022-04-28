#include <iostream>

struct MyNode{
    int value = 0;
    MyNode* nextNode = nullptr;
    MyNode* previousNode = nullptr;
};

struct FIFO{
    MyNode* firstNode = nullptr;
    MyNode* lastNode = nullptr;
};

struct LIFO{
    MyNode* firstNode = nullptr;
    MyNode* lastNode = nullptr;
};

void fifoAdd(int value, FIFO* list){
    MyNode* newNode = static_cast<MyNode *>(malloc(sizeof(MyNode)));
    newNode->value = value;
    newNode->nextNode = nullptr;
    if (list->firstNode == nullptr){
        list->firstNode = newNode;
        std::cout<<"Added value: "<<value<<" at start!"<<std::endl;
    }
    else{
        list->lastNode->nextNode = newNode;
        std::cout<<"Added value: "<<value<<std::endl;
    }
    list->lastNode = newNode;
}

void fifoPop(FIFO* list){
    if (list->firstNode == nullptr){
        std::cout<<"No value to pop!"<<std::endl;
        return;
    }

    std::cout<<"Value popped: "<<list->firstNode->value<<std::endl;
    list->firstNode = list->firstNode->nextNode;
}

void lifoAdd(int value, LIFO* list){
    MyNode* newNode = static_cast<MyNode *>(malloc(sizeof(MyNode)));
    newNode->value = value;
    newNode->nextNode = nullptr;
    newNode->previousNode = nullptr;
    if (list->firstNode == nullptr){
        list->firstNode = newNode;
        std::cout<<"Added value: "<<value<<" at start!"<<std::endl;
    }
    else{
        newNode->previousNode = list->lastNode;
        list->lastNode->nextNode = newNode;
        std::cout<<"Added value: "<<value<<std::endl;
    }
    list->lastNode = newNode;
}

void lifoPop(LIFO* list){
    if (list->lastNode == nullptr){
        std::cout<<"No value to pop!"<<std::endl;
        return;
    }
    std::cout<<"Value popped: "<<list->lastNode->value<<std::endl;
    if (list->lastNode->previousNode == nullptr){
        list->lastNode = nullptr;
    }
    else{
        list->lastNode = list->lastNode->previousNode;
        list->lastNode->nextNode = nullptr;
    }
}


int main() {
    FIFO* list1 = static_cast<FIFO *>(malloc(sizeof(FIFO)));
    list1->firstNode = nullptr;
    list1->lastNode = nullptr;
    LIFO* list2 = static_cast<LIFO *>(malloc(sizeof(LIFO)));
    list2->firstNode = nullptr;
    list2->lastNode = nullptr;
    std::cout<<"FIFO"<<std::endl;
    for (int i = 1;i<=100;i++){
        fifoAdd(i, list1);
    }
    for (int i = 1;i<=102;i++){
        fifoPop(list1);
    }
    std::cout<<"LIFO"<<std::endl;
    for (int i = 1;i<=100;i++){
        lifoAdd(i,list2);
    }
    for (int i = 1;i<=102;i++){
        lifoPop(list2);
    }
    free(list1);
    free(list2);
    return 0;
}