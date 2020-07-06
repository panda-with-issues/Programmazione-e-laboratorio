;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_8) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks")) #f)))
;; task 1

(define locate-bigger                    ; val: list of integer couples, first number representing the peg and second representing number of disks in stack at a given k move
  (lambda (n k s d t s-num d-num t-num)  ; n: positive integer, disks number
                                         ; k: non-negative integer, move number
                                         ; s, d, t: integer representing source, destination, and transition pegs
                                         ; s-num, d-num, t-num: non-negative integers, disks number on s, d, t
    (let ((half (expt 2 (sub1 n))))
      (cond
        ; base case
        ((zero? n)
         (list (list s s-num) (list d d-num) (list t t-num))
         )
        ; recursive steps:
        ; case 1: bigger disk is still on s, we must move n-1 tower from s to t
        ((< k half)
         (locate-bigger (sub1 n) k s t d (add1 s-num) t-num d-num)
         )
        ; case 2: bigger disk is in d, we are moving n-1 tower from t to d
        (else
         (locate-bigger (sub1 n) (- k half) t d s t-num (add1 d-num) s-num)
         )
        )
      )
    ))

(define hanoi-disks                  ; val: list of couples (same as locate-bigger)
  (lambda (n k)                      ; n: positive integer, disks number, k: non-negative integer, move number
    (locate-bigger n k 1 2 3 0 0 0)
    ))

;; test

(hanoi-disks 3 0)      ;'((1 3) (3 0) (2 0)) 
(hanoi-disks 3 1)      ;'((3 0) (2 1) (1 2)) 
(hanoi-disks 3 2)      ;'((2 1) (1 1) (3 1))
(hanoi-disks 3 3)      ;'((1 1) (3 2) (2 0))
(hanoi-disks 3 4)      ;'((3 2) (2 1) (1 0))
(hanoi-disks 3 5)      ;'((2 1) (1 1) (3 1))
(hanoi-disks 3 6)      ;'((1 1) (3 0) (2 2))
(hanoi-disks 3 7)      ;'((3 0) (2 3) (1 0))
(hanoi-disks 5 13)     ;'((3 2) (2 1) (1 2))
(hanoi-disks 15 19705) ;'((3 4) (2 9) (1 2))
(hanoi-disks 15 32767) ;'((3 0) (2 15) (1 0)

;; task 2

(define draw-bigger                                     ; val: image, picture of n disks stacked on pegs at a given k move 
  (lambda (n k s d t s-num d-num t-num disks-to-place)  ; n: positive integer, total number of disks
                                                        ; k: non-negative integer, move number
                                                        ; s, d, t: integer representing source, destination, and transition peg
                                                        ; s-num, d-num, t-num: non-negative integers representings disks stacked on s, d, t pegs. Will be used
                                                        ;                      to keep track of disks height on pegs
                                                        ; disks-to-place: non-negative integer, number of disks yet to be drawn
    (let ((half (expt 2 (sub1 disks-to-place))))  ; we can't use n as counter because n must be constant in order to make disk-image work without raising exception
      (cond
        ; base case; sadly can't be 0 because disk-image would raise exception
        ((= disks-to-place 1)
         (if (zero? k)
             (disk-image disks-to-place n s s-num)
             (disk-image disks-to-place n d d-num)
             )
         )
        ; recursive steps, at each recursion only the biggest disk is drawn
        ((< k half)
         (above (draw-bigger n k s t d (add1 s-num) t-num d-num (sub1 disks-to-place))
                (disk-image disks-to-place n s s-num)
                )
         )
        (else
         (above (draw-bigger n (- k half) t d s t-num (add1 d-num) s-num (sub1 disks-to-place))
                (disk-image disks-to-place n d d-num)
                )
         )
        )
      )
    ))

(define hanoi-picture                         ; val: image with background
  (lambda (n k)                               ; n: positive integer, total number of disks; k: non-negative integer, move number
    (above (draw-bigger n k 1 2 3 0 0 0 n)
           (towers-background n)
           )
    ))

;; tests

(hanoi-picture 5 0)
(hanoi-picture 5 13)
(hanoi-picture 5 22)
(hanoi-picture 5 31)
(hanoi-picture 15 19705)