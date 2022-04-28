#include <iostream>
#include <chrono>
#include <random>

struct MyNode {
    int value = 0;
    MyNode *nextNode = nullptr;
};

struct LinkedList {
    MyNode *firstNode = nullptr;
};

void show(LinkedList *list) {
    MyNode *currentNode = list->firstNode;
    while (currentNode != nullptr) {
        std::cout << currentNode->value << " ";
        currentNode = currentNode->nextNode;
    }
    std::cout << std::endl;
}

void show(LinkedList *list, unsigned int id) {
    MyNode *currentNode = list->firstNode;
    if (currentNode == nullptr) {
        std::cout << "No element with this index!" << std::endl;
        return;
    }
    for (int i = 0; i < id; i++) {
        if (currentNode->nextNode == nullptr) {
            std::cout << "No element with this index!" << std::endl;
            return;
        }
        currentNode = currentNode->nextNode;
    }
    //std::cout << "Value: " << currentNode->value << " Index: " << id << std::endl;
}

void addLL(int value, LinkedList *list) {
    MyNode *newNode = static_cast<MyNode *>(malloc(sizeof(MyNode)));
    newNode->value = value;
    newNode->nextNode = nullptr;
    if (list->firstNode == nullptr) {
        list->firstNode = newNode;
        return;
    }

    MyNode *currentNode = list->firstNode;
    while (currentNode->nextNode != nullptr) {
        currentNode = currentNode->nextNode;
    }
    currentNode->nextNode = newNode;
}

void addLL(int value, LinkedList *list, unsigned int id) {
    MyNode *newNode = static_cast<MyNode *>(malloc(sizeof(MyNode)));
    newNode->value = value;
    newNode->nextNode = nullptr;

    MyNode *currentNode = list->firstNode;
    MyNode *previousNode;
    if (currentNode == nullptr) {
        if (id == 0) {
            list->firstNode = newNode;
            std::cout << "Added at the start value: " << value << " on id: " << id << std::endl;
        } else {
            std::cout << "No element with this index!" << std::endl;
        }
        return;
    }

    if (id == 0) {
        list->firstNode = newNode;
        newNode->nextNode = currentNode;
        std::cout << "Added at the start value: " << value << " on id: " << id << std::endl;
        return;
    }

    for (int i = 0; i < id; i++) {
        if (currentNode->nextNode == nullptr) {
            if (i + 1 == id) {
                currentNode->nextNode = newNode;
                std::cout << "Adding at the end value: " << value << " on id: " << id << std::endl;
            } else {
                std::cout << "No element with this index!" << std::endl;
            }
            return;
        }
        previousNode = currentNode;
        currentNode = currentNode->nextNode;
    }

    newNode->nextNode = currentNode;
    previousNode->nextNode = newNode;
    std::cout << "Added value: " << value << " on id: " << id << std::endl;
}

void deleteLL(LinkedList *list) {
    if (list->firstNode == nullptr) {
        std::cout << "No value to pop!" << std::endl;
        return;
    }
    MyNode *currentNode = list->firstNode;
    if (currentNode->nextNode == nullptr) {
        list->firstNode = nullptr;
    }
    MyNode *previousNode;
    while (true) {
        if (currentNode->nextNode == nullptr) {
            previousNode->nextNode = nullptr;
            break;
        }
        previousNode = currentNode;
        currentNode = currentNode->nextNode;
    }
}

void deleteLL(LinkedList *list, unsigned int id) {
    if (list->firstNode == nullptr) {
        std::cout << "No value to pop!" << std::endl;
        return;
    }

    if (id == 0) {
        list->firstNode = list->firstNode->nextNode;
        return;
    }

    MyNode *currentNode = list->firstNode;
    MyNode *previousNode;
    for (int i = 0; i <= id; i++) {
        if (currentNode->nextNode == nullptr) {
            if (i + 1 == id) {
                previousNode->nextNode = nullptr;
            } else {
                std::cout << "Wrong index!" << std::endl;
            }
            return;
        }
        previousNode = currentNode;
        currentNode = currentNode->nextNode;
    }
    previousNode->nextNode = currentNode->nextNode;
}

void merge(LinkedList *list1, LinkedList *list2) {
    if (list1->firstNode == nullptr) {
        list1->firstNode = list2->firstNode;
        return;
    }
    if (list2->firstNode == nullptr) {
        return;
    }
    MyNode* currentNode = list1->firstNode;
    while(currentNode->nextNode != nullptr){
        currentNode = currentNode->nextNode;
    }
    currentNode->nextNode = list2->firstNode;
}

int main() {
    LinkedList *list1 = static_cast<LinkedList *>(malloc(sizeof(LinkedList)));
    LinkedList *list2 = static_cast<LinkedList *>(malloc(sizeof(LinkedList)));
    list1->firstNode = nullptr;
    list2->firstNode = nullptr;

    for (int i = 0; i < 1001; i++) {
        addLL(i, list1);
        addLL(i*2, list2);
    }
    for(int i=0;i<=1000;i+=100){
        long long nanoseconds = 0;
        for(int j=0;j<1000000;j++){
            auto start = std::chrono::steady_clock::now();
            show(list1,i);
            auto end = std::chrono::steady_clock::now();
            nanoseconds+= std::chrono::duration_cast<std::chrono::nanoseconds>(end - start).count();
        }
        nanoseconds/=1000000;
        std::cout << "Average time for index " << i << ": " << nanoseconds << " nanoseconds" << std::endl;
    }
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> distr(0,1000);
    long long nanoseconds = 0;
    for (int i = 0; i<1000;i++){
        int index = distr(gen);
        for (int j = 0;j<1000;j++){
            auto start = std::chrono::steady_clock::now();
            show(list1, index);
            auto end = std::chrono::steady_clock::now();
            nanoseconds+= std::chrono::duration_cast<std::chrono::nanoseconds>(end - start).count();
        }
    }
    nanoseconds/=1000*1000;
    std::cout << "Average time for random number: " << nanoseconds << " nanoseconds" << std::endl;

    std::cout<<std::endl;
    std::cout<<"Merging Lists:"<<std::endl;
    LinkedList *list3 = static_cast<LinkedList *>(malloc(sizeof(LinkedList)));
    LinkedList *list4 = static_cast<LinkedList *>(malloc(sizeof(LinkedList)));
    list3->firstNode = nullptr;
    list4->firstNode = nullptr;

    for (int i = 0; i < 10; i++) {
        addLL(i, list3);
        addLL(i*2, list4);
    }
    std::cout<<"List1: ";
    show(list3);
    std::cout<<"List2: ";
    show(list4);
    std::cout<<"After merge: ";
    merge(list3,list4);
    show(list3);
    std::cout<<std::endl;


    free(list1);
    free(list2);
    free(list3);
    free(list4);
    return 0;
}