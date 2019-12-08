;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_8) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;; given procedures

(define hanoi-moves     ; val: list of couples, each representing from_peg and to_peg of an optimal move
  (lambda (n)           ; n: disks number, positive
    (hanoi-rec n 1 2 3)
    ))

(define hanoi-rec   ; val: list of couples
  (lambda (n s d t) ; n: disks number, s: source peg, d: destination peg, t: transition peg
    (if (= n 1)
        (list (list s d))
        (let ((m1 (hanoi-rec (- n 1) s t d))
              (m2 (hanoi-rec (- n 1) t d s))
              )
          (append m1 (cons (list s d) m2))
          ))
    ))

;; task 1

(define legal-move
  (lambda (n k s d t)
    (let ((half (expt 2 (sub1 n)))
          (r (remainder k 3))
          )
      (if (<= k half)
          (cond
            ((= r 0)
             (list d t)
             )
            ((= r 1)
             (list s d)
             )
            ((= r 2)
             (list s t)
             )
            )
          (cond
            ((= r 0)
             (list t d)
             )
            ((= r 1)
             (list s d)
             )
            ((= r 2)
             (list t s)
             )
            )
          )
      )
    ))

(define moves-list
  (lambda (n k)
    (if (zero? k)
        null
        (append (moves-list n (sub1 k)) (list (legal-move n k 1 2 3)) )
        )
    ))

(define move-disks
  (lambda (moves s d t disks)
    (if (null? moves)
        disks
    (let ((move (car moves))
          (s* (car move))
          (d* (cdr move))
          (s-num (car disks))
          (d-num (car (cdr disks)))
          (t-num (cdr (cdr disks)))
          )
      (cond
        ((= s* s)
         (cond
           ((= d* d)  ; s -> d
            (move-disks (cdr moves) s d t (list (sub1 s-num) (add1 d-num) t-num))
            )
           ((= d* t)  ; s -> t
            (move-disks (cdr moves) s d t (list (sub1 s-num) d-num (add1 t-num)))
            )
           )
         )
        ((= s* d)
         (cond
           ((= d* s)  ; d -> s
            (move-disks (cdr moves) s d t (list (add1 s-num) (sub1 d-num) t-num))
            )
           ((= d* t)  ; d -> t
            (move-disks (cdr moves) s d t (list s-num (sub1 d-num) (add1 t-num)))
            )
           )
         )
        ((= s* t)
         (cond
           ((= d* s)  ; t -> s
            (move-disks (cdr moves) s d t (list (add1 s-num) d-num (add1 t-num)))
            )
           ((= d* d)  ; t -> d
            (move-disks (cdr moves) s d t (list s-num (add1 d-num) (sub1 t-num)))
            )
           )
         )
        )
      )
      )
    ))

(define disk-position
  (lambda (n k s d t disks)
   (if (null? (moves-list n k))
       disks
       (let (move))
       )
    ))

;; test

;(hanoi-disks 3 0)      ;'((1 3) (3 0) (2 0))
;(hanoi-disks 3 1)      ;'((3 0) (2 1) (1 2))
;(hanoi-disks 3 2)      ;'((2 1) (1 1) (3 1))
;(hanoi-disks 3 3)      ;'((1 1) (3 2) (2 0))
;(hanoi-disks 3 4)      ;'((3 2) (2 1) (1 0))
;(hanoi-disks 3 5)      ;'((2 1) (1 1) (3 1))
;(hanoi-disks 3 6)      ;'((1 1) (3 0) (2 2))
;(hanoi-disks 3 7)      ;'((3 0) (2 3) (1 0))
;(hanoi-disks 5 13)     ;'((3 2) (2 1) (1 2))
;(hanoi-disks 15 19705) ;'((3 4) (2 9) (1 2))
;(hanoi-disks 15 32767) ;'((3 0) (2 15) (1 0)