#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include<sys/time.h>

#define SUBMITTED 0
#define RUNNING 1
#define FINISHED 2

struct Program
{
	int prog_id;
	char prog_name[50];
	char uid_executing[25];
	u_int64_t start_time;
	u_int64_t time_elapsed;
	int prog_status;
	struct Program *next;
	struct Program *prev;

} *head = NULL, *tail = NULL, *temp = NULL;

int pid = 1;

void showAll()
{
	int flag = 0;
	const char *s[3] = {"Submitted", "Running", "Finished"};

	if (!head)
	{
		printf("\nNo Program found...\n");
		return;
	}
	else
	{
		temp = head;
		while (temp)
		{
			printf("\nProgram ID: %d", temp->prog_id);
			printf("\nProgram Name: %s", temp->prog_name);
			printf("\nUID_Executing: %s", temp->uid_executing);
			printf("\nStart Time: %ld", temp->start_time);
			printf("\nTime Elapsed: %ld", temp->time_elapsed);
			printf("\nProgram Status: %s\n", s[temp->prog_status]);
			
			temp = temp->next;
			flag = 1;
		}
	}

	while (flag)
	{
		printf("\nPress 0 to continue: ");
		scanf("%d", &flag);
	}

	temp = NULL;
}

u_int64_t getCurrentTime()
{
	struct timeval tv;
	if (gettimeofday (&tv, NULL) == 0)
		return (u_int64_t) (tv.tv_sec * 1000000 + tv.tv_usec);
	else
		return 0;
}

void newProgram()
{
	char name[50], uid[25];

	printf("\nEnter Program name: ");
	scanf("%[^\n]s", name);
	getchar();
	
	printf("\nEnter User executing Id: ");
	scanf("%[^\n]s", uid);
	getchar();

	struct Program *node;
	node = (struct Program *)malloc(sizeof(struct Program));
	strcpy(node->prog_name, name);
	strcpy(node->uid_executing, uid);
	node->prog_status = SUBMITTED;
	node->start_time = getCurrentTime();
	node->time_elapsed = 0;
	node->next = node->prev = NULL;

	if (!head)
	{
		node->prog_id = pid++;
		head = node;
		tail = node;
	}
	else
	{
		node->prog_id = pid++;
		node->prev = tail;
		tail->next = node;
		tail = node;
	}

	printf("\n$->new program is added with status as submitted\n");

	node = NULL;
}

void updateToRunning()
{
	int id;
	int flag = 1;

	if (head)
	{
		temp = head;
		while (temp)
		{
			if (temp->prog_status == SUBMITTED)
			{
				if (flag)
					printf("\n PID's\t| Program name's");
				printf("\n %d\t| %s", temp->prog_id, temp->prog_name);

				flag = 0;
			}
			temp = temp->next;
		}

		if (!flag)
		{
			printf("\n\nEnter program id to start: ");
			scanf("%d", &id);
			getchar();
		}
		else
		{
			printf("\nNo program are in submitted stat...\n");
			return;
		}

		temp = head;
		while (temp)
		{
			if (temp->prog_id == id && temp->prog_status == SUBMITTED)
			{
				flag = 1;
				temp->prog_status = RUNNING;
				printf("\nprogram id %d is now in running stat...\n", id);
				break;
			}

			temp = temp->next;
		}

		if (!flag)
			printf("\nWrong id...\n");
	}
	else
		printf("\nNo program found...\n");

	temp = NULL;
}

void updateTimeElapsed()
{
	if (!head)
	{
		printf("\nNo program found...\n");
		return;
	}

	temp = head;
	while (temp)
	{
		if (temp->prog_status == RUNNING)
			temp->time_elapsed += 1000000;
		temp = temp->next;
	}

	printf("\nAll running programs time elapsed has been updated...\n");

	temp = NULL;
}

void finishProgram()
{
	int id;
	int flag = 1;

	if (head)
	{
		temp = head;
		while (temp)
		{
			if (temp->prog_status == RUNNING)
			{
				if (flag)
					printf("\n PID's\t| Program name's");
				printf("\n %d\t| %s", temp->prog_id, temp->prog_name);

				flag = 0;
			}
			temp = temp->next;
		}

		if (!flag)
		{
			printf("\n\nEnter program id to finish: ");
			scanf("%d", &id);
			getchar();
		}
		else
		{
			printf("\nNo program are in running stat...\n");
			return;
		}

		temp = head;
		while (temp)
		{
			if (temp->prog_id == id && temp->prog_status == RUNNING)
			{
				flag = 1;
				temp->prog_status = FINISHED;
				printf("\nProgram id %d is now in finished stat...\n", id);
				break;
			}

			temp = temp->next;
		}

		if (!flag)
			printf("\nWrong id...\n");
	}
	else
		printf("\nNo program found...\n");

	temp = NULL;
}

void removeAllFinished()
{

	if (!head)
	{
		printf("\nNo program found...\n");
		return;
	}

	temp = head;
	while (temp)
	{
		if (temp->prog_status == FINISHED)
		{
			struct Program *t;
			t = (struct Program *)malloc(sizeof(struct Program));

			if (temp == head && head == tail)
			{
				t = temp;
				head = tail = NULL;
			}
			else if (temp == head)
			{
				t = head;
				head = head->next;
				head->prev = NULL;
			}
			else if (temp == tail)
			{
				t = tail;
				tail = tail->prev;
				tail->next = NULL;
			}
			else
			{
				t = temp;
				temp = t->prev;
				temp->next = t->next;
				temp = t->next;
				temp->prev = t->prev;
			}

			free(t);
		}
		temp = temp->next;
	}

	printf("\nAll finished programs are removed...\n");

	temp = NULL;
}

void exitSystem()
{
	temp = head;
	while (temp)
	{
		head = head->next;
		free(temp);
		temp = head;
	}

	printf("\nProcess finished with exit code 0\n");
	sleep(1);
	system("clear");
	exit(0);
}

int main(int argc, char *argv[])
{
	void (*options[7])() = {newProgram, updateToRunning, updateTimeElapsed, finishProgram, removeAllFinished, showAll, exitSystem};

	int choice;

	while (1)
	{
		system("clear");
		printf("\n----------- M E N U -----------");
		printf("\n1) Add New Program");
		printf("\n2) Start Program");
		printf("\n3) Update Program time");
		printf("\n4) Finish Program");
		printf("\n5) Remove All Finished Program");
		printf("\n6) Show All Program");
		printf("\n7) Exit");
		printf("\n-------------------------------");
		printf("\n\nEnter Your Choice: ");
		scanf("%d", &choice);
		getchar();

		if (1 <= choice && choice <= 7)
			options[choice - 1]();
		else
			printf("\nInvalid Choice..!!!\n");

		sleep(5);
	}

	return 0;
}
