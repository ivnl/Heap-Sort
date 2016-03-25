#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
using namespace std;

ifstream in_data;
ofstream out_data1;
ofstream out_data2;

class HeapSort {

    private:

        int* heapAry;
        int size_array;

    public:

        HeapSort(int size) {

            heapAry = new int[size]();
            size_array = size;

            }

        ~HeapSort() {

            delete[] heapAry;

            }
            
            
            
bool isHeapFull() {

            if(heapAry[0]==size_array-1) return true;
            else return false;

            }

void insertOneDataItem(int newnum) {

     if(isHeapFull()==true) { //check if array is full

        cout<<"\nArray full on attempt to insert: "<<newnum<<"\n\n";
        return;
                
        }

      heapAry[0]++; //increase counter of elements with each insertion call
      heapAry[heapAry[0]] = newnum;
      bubbleUp();

   }
            

void bubbleUp() {

     int current_Index = heapAry[0];

     while( heapAry[current_Index] < heapAry[current_Index/2] && current_Index!=1) {

		int temp = heapAry[current_Index]; //save current value of index to temp
		heapAry[current_Index] = heapAry[current_Index/2]; // tranfser value of larger parent to child slot (move down)
		heapAry[current_Index/2] = temp; //transfer value of temp (saved child value) to parent;
        current_Index = current_Index/2; //set new index to next parent
                }
            }

        bool isHeapEmpty() {
            if(heapAry[0]==0) return true;
            else return false;

            }


int deleteRoot() {

     int root = heapAry[1];

     if(isHeapEmpty()==true) {
        cout<<"\nArray Empty!\n";
        return 0;
                }

            heapAry[1] = heapAry[heapAry[0]]; //'set' last leaf on the heap to root

            heapAry[heapAry[0]]=0; //'delete' last leaf on the heap

            heapAry[0]--;

			//if (heapAry[0] == 1) return root;
			if (heapAry[0] > 0) bubbleDown();

            return root;

            }
            
void buildHeap(ifstream& in_file, ofstream& out_file) {
		int num;
		while (in_file >> num) {
			out_file << "inserting: " << setw(3) << setfill(' ') << num << "\t| ";
			insertOneDataItem(num);
			print(out_file, 10);
			
        }
        
    }
    
   
    
void print(ofstream& out_file, int limit) {
		
	for (int i = 1; i <= heapAry[0] && i <= limit; i++)
			out_file << setw(3) << setfill(' ') << heapAry[i] << " ";
			
		out_file << endl;

	}
	
void deleteHeap(ofstream& out_file) {
		
	while (!isHeapEmpty()) {
			out_file << "deleting: " << setw(3) << setfill(' ') << deleteRoot() << "\t|  ";
			print(out_file, 10);
		}
		
		out_file << endl;
			
		}

            
            
void bubbleDown() {

	int currentIndex, minChild;
	currentIndex = 1;//begin at 1 (root)

    while(currentIndex < heapAry[0]) {

        if(currentIndex*2+1 <= heapAry[0]) {
            if(heapAry[currentIndex*2] < heapAry[currentIndex*2+1]) minChild = currentIndex*2;
            else minChild = currentIndex*2+1;
            }

        else if(currentIndex*2 <= heapAry[0]) minChild = currentIndex*2;

        else return;

        if(heapAry[currentIndex] > heapAry[minChild]) {
            int temp = heapAry[currentIndex];
            heapAry[currentIndex] = heapAry[minChild];
            heapAry[minChild] = temp;
            }

        else return;

        currentIndex = minChild;

        }
    }
 };
 

int main(int argc, char * argv[]) {
	
	in_data.open(argv[1]);
    out_data1.open(argv[2]);
	out_data2.open(argv[3]);

    int count,num;

    if (in_data.is_open()) {
        while (in_data>>num) {
            count++; //count num of elements in txt file
            out_data1<<num<<" ";
            }

        out_data1<<"\n"<<"Total number of elements found in file: "<<count<<"\n\n";

		in_data.close();

        }
          
    HeapSort myHeap (count+1); 
    in_data.open(argv[1]);
    myHeap.buildHeap(in_data, out_data1);
    myHeap.deleteHeap(out_data2);

    return 0;
    }

