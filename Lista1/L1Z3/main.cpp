#include <iostream>
#include <chrono>
#include <random>

struct MyNode {
    int value = 0;
    MyNode *nextNode = nullptr;
    MyNode *previousNode = nullptr;
};

struct CircuralDoublyLinkedList {
    MyNode *firstNode = nullptr;
    int length = 0;
};

void show(CircuralDoublyLinkedList *list) {
    if (list->firstNode == nullptr) {
        std::cout << "Empty list!" << std::endl;
        return;
    }
    MyNode *currentNode = list->firstNode;
    do {
        std::cout << currentNode->value << " ";
        currentNode = currentNode->nextNode;
    } while (currentNode != list->firstNode);
    std::cout << std::endl;
}

void show(CircuralDoublyLinkedList *list, int id) {
    if (list->firstNode == nullptr) {
        std::cout << "No element with this index!" << std::endl;
        return;
    }
    if (id >= list->length) {
        std::cout << "Wrong index!" << std::endl;
        return;
    }
    MyNode *currentNode = list->firstNode;

    if (id > (list->length) / 2) {
        currentNode = currentNode->previousNode;
        for (int i = list->length - 1; i > id; i--) {
            currentNode = currentNode->previousNode;
        }
    } else {
        for (int i = 0; i < id; i++) {
            currentNode = currentNode->nextNode;
        }
    }

    //std::cout << "Value: " << currentNode->value << " Index: " << id << std::endl;
}

void addCDLL(int value, CircuralDoublyLinkedList *list) {
    list->length++;
    MyNode *newNode = static_cast<MyNode *>(malloc(sizeof(MyNode)));
    newNode->value = value;
    newNode->nextNode = nullptr;
    newNode->previousNode = nullptr;
    if (list->firstNode == nullptr) {
        list->firstNode = newNode;
        newNode->nextNode = newNode;
        newNode->previousNode = newNode;
        return;
    }

    MyNode *lastNode = list->firstNode->previousNode;
    lastNode->nextNode = newNode;
    list->firstNode->previousNode = newNode;
    newNode->previousNode = lastNode;
    newNode->nextNode = list->firstNode;
}

void addCDLL(int value, CircuralDoublyLinkedList *list, int id) {
    if (id > list->length) {
        std::cout << "Wrong index!" << std::endl;
        return;
    }
    MyNode *newNode = static_cast<MyNode *>(malloc(sizeof(MyNode)));
    newNode->value = value;
    if (list->firstNode == nullptr) {
        list->firstNode = newNode;
        newNode->nextNode = newNode;
        newNode->previousNode = newNode;
        list->length++;
        return;
    }

    MyNode *currentNode = list->firstNode;

    if (id == 0) {
        list->firstNode = newNode;
    } else if (id > list->length / 2 && id != list->length) {
        currentNode = currentNode->previousNode;
        std::cout << currentNode->value << std::endl;
        for (int i = list->length - 1; i > id; i--) {
            currentNode = currentNode->previousNode;
        }
    } else {
        for (int i = 0; i < id; i++) {
            currentNode = currentNode->nextNode;
        }
    }
    MyNode *previousNode = currentNode->previousNode;
    previousNode->nextNode = newNode;
    currentNode->previousNode = newNode;
    newNode->previousNode = previousNode;
    newNode->nextNode = currentNode;
    list->length++;
}

void deleteCDLL(CircuralDoublyLinkedList *list) {
    if (list->firstNode == nullptr) {
        std::cout << "No value to pop!" << std::endl;
        return;
    }
    list->length--;
    if (list->length == 1) {
        list->firstNode = nullptr;
        return;
    }

    MyNode *newLastNode = list->firstNode->previousNode->previousNode;
    newLastNode->nextNode = list->firstNode;
    list->firstNode->previousNode = newLastNode;

}

void deleteCDLL(CircuralDoublyLinkedList *list, int id) {
    if (list->firstNode == nullptr) {
        std::cout << "No value to pop!" << std::endl;
        return;
    }
    if (id >= list->length) {
        std::cout << "Wrong index!" << std::endl;
        return;
    }

    list->length--;
    if (list->length == 1) {
        list->firstNode = nullptr;
        return;
    }

    MyNode* currentNode = list->firstNode;
    if (id == 0 ){
        list->firstNode = list->firstNode->nextNode;
    }
    else if(id > list->length / 2){
        currentNode = currentNode->previousNode;
        std::cout<<currentNode->value<<std::endl;
        for (int i = list->length - 1; i > id-1; i--) {
            currentNode = currentNode->previousNode;
        }
    }
    else{
        for(int i = 0; i<id ;i++){
            currentNode = currentNode->nextNode;
        }
    }
    MyNode* previousNode = currentNode->previousNode;
    previousNode->nextNode = currentNode->nextNode;
    currentNode->nextNode->previousNode = previousNode;
}

void merge(CircuralDoublyLinkedList *list1, CircuralDoublyLinkedList *list2) {
    if (list1->firstNode == nullptr) {
        list1->firstNode = list2->firstNode;
        return;
    }
    if (list2->firstNode == nullptr) {
        return;
    }

    MyNode* lastNode = list2->firstNode->previousNode;
    list1->firstNode->previousNode->nextNode = list2->firstNode;
    lastNode->nextNode = list1->firstNode;
    list2->firstNode->previousNode = list1->firstNode->previousNode;
    list1->firstNode->previousNode = lastNode;
    list1->length+=list2->length;
}

int main() {
    CircuralDoublyLinkedList *list1 = static_cast<CircuralDoublyLinkedList *>(malloc(sizeof(CircuralDoublyLinkedList)));
    list1->firstNode = nullptr;
    list1->length = 0;
    CircuralDoublyLinkedList *list2 = static_cast<CircuralDoublyLinkedList *>(malloc(sizeof(CircuralDoublyLinkedList)));
    list2->firstNode = nullptr;
    list2->length = 0;

    for (int i = 1; i <= 1001; i++) {
        addCDLL(i, list1);
        addCDLL(i + 5, list2);
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
    CircuralDoublyLinkedList *list3 = static_cast<CircuralDoublyLinkedList *>(malloc(sizeof(CircuralDoublyLinkedList)));
    CircuralDoublyLinkedList *list4 = static_cast<CircuralDoublyLinkedList *>(malloc(sizeof(CircuralDoublyLinkedList)));
    list3->firstNode = nullptr;
    list4->firstNode = nullptr;

    for (int i = 0; i < 10; i++) {
        addCDLL(i, list3);
        addCDLL(i * 2, list4);
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