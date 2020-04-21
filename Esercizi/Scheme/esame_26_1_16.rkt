;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname esame_26_1_16) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define digit?
  (lambda (char)
    (if (and (>= (char->integer char) (char->integer #\1))
             (<= (char->integer char) (char->integer #\9))
             )
        #t
        #f
        )
    ))

(define letter?
  (lambda (char)
    (if (and (>= (char->integer char) (char->integer #\a))
             (<= (char->integer char) (char->integer #\z))
             )
        #t
        #f
        )))

(define LETTER?
  (lambda (char)
    (if (and (>= (char->integer char) (char->integer #\A))
             (<= (char->integer char) (char->integer #\Z))
             )
        #t
        #f
        )))

(define word?
  (lambda (char)
    (or (letter? char) (LETTER? char) (digit? char))
    ))

(define next-word
  (lambda (str)
    (if (or (string=? str "") (not (word? (string-ref str 0))))
        str
        (next-word (substring str 1))
        )))

(define word-count
  (lambda (str)
    (cond ((string=? str "")
           0
           )
          ((word? (string-ref str 0))
           (+ 1 (word-count (next-word str)))
           )
          (else
           (word-count (substring str 1))
           )
          )))

(word-count "") ;0
(word-count "  ... ?; ") ;0
(word-count "3o esercizio della prova scritta di PROGRAMMAZIONE.") ;7
(word-count " --- film:  L'albero degli zoccoli (1978)  / regista:  E. Olmi ") ;9